package com.darkmi.vvs;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.darkmi.vvs.session.RtspSession;
import com.darkmi.vvs.session.RtspSessionAccessor;
import com.darkmi.vvs.session.RtspSessionKeyFactory;
import com.darkmi.vvs.session.SimpleRandomKeyFactory;

import cn.com.supertv.entity.rm.VideoTypeEnum;
import cn.com.supertv.srmserver.rtsp.RtspCode;
import cn.com.supertv.srmserver.rtsp.RtspHeaderCode;
import cn.com.supertv.srmserver.rtsp.RtspRequest;
import cn.com.supertv.srmserver.rtsp.RtspResponse;
import cn.com.supertv.srmserver.rtsp.RtspTransport;
import cn.com.supertv.srmserver.rtsp.TransportTypeEnum;
import cn.com.supertv.srmserver.util.DateUtil;

/**
 * 处理SETUP,TEARDOWN.
 */
@Component
public class SetupMessageHandler extends IoHandlerAdapter {
	private Logger logger = LoggerFactory.getLogger(SetupMessageHandler.class);
	private static final String CSEQ = "cseq";
	private static final String REQUIRE_VALUE_HFC = "HFC.Delivery.Profile.1.0";
	private static final String REQUIRE_VALUE_NGOD_R2 = "com.comcast.ngod.r2";
	private RtspSessionAccessor sessionAccessor;
	private static RtspSessionKeyFactory keyFactory = new SimpleRandomKeyFactory();
	private String vvsIpAddress;
	private int playPort;

	/**
	 * 接收到数据,进行处理.
	 */
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		logger.debug("vvs 接收到客户端请求:\n{}", message);
		RtspRequest request = (RtspRequest) message;

		//保存cseq
		session.setAttribute(CSEQ, request.getHeader(RtspHeaderCode.CSeq));

		switch (request.getVerb()) {
		case OPTIONS:
			onRequestOptions(session, request);
			break;
		case DESCRIBE:
			onRequestDescribe(session, request);
			break;
		case SETUP:
			onRequestSetup(session, request);
			break;
		case TEARDOWN:
			onRequestTeardown(session, request);
			break;
		default:
			onDefaultRequest(session, request, request.getHeader(RtspHeaderCode.CSeq));
		}

		//由SRM关闭
		//session.close(true);
	}

	/**
	 * 捕捉到异常,进行处理.
	 */
	public void exceptionCaught(IoSession session, Throwable cause) {
		logger.debug("vvs(554) exceptionCaught begin { ");
		logger.error(cause.getMessage(), cause);

		//获取cseq
		String cseq = (String) session.getAttribute(CSEQ);
		if (null == cseq || "".equals(cseq)) {
			handleError(session, "0", RtspCode.InternalServerError);
		} else {
			handleError(session, cseq, RtspCode.InternalServerError);
		}

		//session.close(true);
		logger.debug("vvs(554) exceptionCaught end } ");
	}

	/**
	 * 处理OPTIONS命令.
	 */
	private void onRequestOptions(IoSession session, RtspRequest request) {
		logger.debug("vvs Options begin { ");
		RtspResponse response = new RtspResponse();
		response.setHeader(RtspHeaderCode.Public, "DESCRIBE, SETUP, TEARDOWN");
		response.setHeader(RtspHeaderCode.Server, "MediaHawk");
		response.setHeader(RtspHeaderCode.ContentLength, "0");
		session.write(response);
		logger.debug("vvs Options end } ");
	}

	/**
	 * 处理DESCRIBE命令.
	 */
	private void onRequestDescribe(IoSession session, RtspRequest request) {
		logger.debug("vvs Describe begin { ");
		RtspResponse response = new RtspResponse();
		response.setHeader(RtspHeaderCode.Public, "DESCRIBE, SETUP, TEARDOWN");
		response.setHeader(RtspHeaderCode.Server, "MediaHawk");
		response.setHeader(RtspHeaderCode.ContentLength, "0");
		session.write(response);
		logger.debug("vvs Describe end } ");
	}

	/**
	 * 处理SETUP命令.
	 * @return
	 */
	private void onRequestSetup(IoSession session, RtspRequest request) {
		logger.debug("vvs handle SETUP request begin { ");

		//获取cesq
		String cseq = request.getHeader(RtspHeaderCode.CSeq);
		if (null == cseq || "".equals(cseq)) {
			logger.error("cesq is null.");
			handleError(session, "0", RtspCode.HeaderFieldNotValidForResource);
			return;
		}

		//根据require选择相应协议(HFC or NGOD)
		String requireValue = request.getHeader(RtspHeaderCode.Require);
		if (null == requireValue || "".equals(requireValue)) {
			logger.error("require value is null.");
			handleError(session, "0", RtspCode.HeaderFieldNotValidForResource);
			return;
		}

		//获取Transport
		String strTransport = request.getHeader(RtspHeaderCode.Transport);
		if (null == strTransport || strTransport.equals("")) {
			logger.error("transport value is null.");
			handleError(session, cseq, RtspCode.UnsupportedTransport);
			return;
		}

		//获取SRM分配的资源
		RtspTransport rtspTransport = null;
		try {
			if (REQUIRE_VALUE_HFC.equalsIgnoreCase(requireValue)) {
				rtspTransport = new RtspTransport(strTransport, VideoTypeEnum.CCUR, TransportTypeEnum.REQUEST);
			} else if (REQUIRE_VALUE_NGOD_R2.equalsIgnoreCase(requireValue)) {
				rtspTransport = new RtspTransport(strTransport, VideoTypeEnum.DILU, TransportTypeEnum.REQUEST);
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
		//获取destination
		String destination = rtspTransport.getDestination();
		if (null == destination || "".equals(destination)) {
			logger.error("destination is null.");
			handleError(session, cseq, RtspCode.UnsupportedTransport);
			return;
		}
		//获取port
		int port = rtspTransport.getQam_port();

		//------------------------
		//
		//省略,驱动底层向IPQAM推流.
		//
		//------------------------

		//创建session并记录使用资源
		String sessionKey = keyFactory.createSessionKey();
		logger.debug("sessionKey --> " + sessionKey);
		RtspSession rtspSession = sessionAccessor.getSession(sessionKey, true);
		rtspSession.setAttribute(destination + ":" + port, "USED");

		if (REQUIRE_VALUE_HFC.equalsIgnoreCase(requireValue)) {
			logger.debug("vvs返回HFC协议响应。。。。。。。。。。。。");
			//构建返回给请求方的响应
			RtspResponse response = new RtspResponse();
			response.setCode(RtspCode.OK);
			response.setHeader(RtspHeaderCode.CSeq, cseq);
			response.setHeader(RtspHeaderCode.Date, DateUtil.getGmtDate());
			response.setHeader(RtspHeaderCode.Session, sessionKey + ";timeout=60");
			response.setHeader(RtspHeaderCode.Transport, rtspTransport.toString());
			response.setHeader(RtspHeaderCode.Range, "npt=0-233.800");
			String location = "rtsp://" + vvsIpAddress + ":" + playPort + "/" + request.getUrl().getUri();
			response.setHeader(RtspHeaderCode.Location, location);
			session.write(response);
		} else if (REQUIRE_VALUE_NGOD_R2.equalsIgnoreCase(requireValue)) {
			logger.debug("vvs返回NGOD协议响应。。。。。。。。。。。。");
			//构建返回给请求方的响应
			RtspResponse response = new RtspResponse();
			response.setCode(RtspCode.OK);
			response.setHeader(RtspHeaderCode.CSeq, cseq);
			response.setHeader(RtspHeaderCode.Date, DateUtil.getGmtDate());
			response.setHeader(RtspHeaderCode.Session, sessionKey + ";timeout=60");
			response.setHeader(RtspHeaderCode.OnDemandSessionId, request.getHeader(RtspHeaderCode.OnDemandSessionId));
			response.setHeader(RtspHeaderCode.Transport, rtspTransport.toString());
			response.setHeader(RtspHeaderCode.Range, "npt=0-233.800");
			response.setHeader(RtspHeaderCode.ContentType, "application/sdp");

			//set sdp extension
			StringBuffer sdp = new StringBuffer();
			sdp.append("v=0\r\n");
			sdp.append("o=- " + sessionKey + " 1339005446 IN IP4 " + vvsIpAddress + "\r\n");
			sdp.append("s=RTSP Session\r\n");
			sdp.append("t=0 0\r\n");
			sdp.append("a=control:rtsp://" + vvsIpAddress + ":" + playPort + "/" + sessionKey + "\r\n");
			sdp.append("c=IN IP4 0.0.0.0\r\n");
			sdp.append("m=video 0 RTP/AVP 33\r\n");

			//设置SDP内容长度
			response.setHeader(RtspHeaderCode.ContentLength, String.valueOf(sdp.length()));
			response.setBuffer(sdp);
			//发送响应
			session.write(response);
		}
		logger.debug("vvs handle SETUP request end } ");
	}

	/**
	 * 处理TEARDOWN命令.
	 */
	private void onRequestTeardown(IoSession session, RtspRequest request) {
		logger.debug("vvs handle TEARDOWN request begin { ");
		//获取cesq
		String cseq = request.getHeader(RtspHeaderCode.CSeq);
		if (null == cseq || "".equals(cseq)) {
			logger.error("cesq is null.");
			handleError(session, "0", RtspCode.HeaderFieldNotValidForResource);
			return;
		}

		//获取sessionKey
		String sessionKey = request.getHeader(RtspHeaderCode.Session);
		if (null == sessionKey || "".equals(sessionKey)) {
			logger.error("sessionKey is null.");
			handleError(session, cseq, RtspCode.SessionNotFound);
			return;
		}

		//获取session
		RtspSession rtspSession = sessionAccessor.getSession(sessionKey, false);
		if (null == rtspSession) {
			logger.error("rtspSession is null.");
			handleError(session, cseq, RtspCode.SessionNotFound);
			return;
		}

		//销毁session
		rtspSession.destroy();

		//构建返回给请求方的响应
		RtspResponse response = new RtspResponse();
		response.setCode(RtspCode.OK);
		response.setHeader(RtspHeaderCode.CSeq, cseq);
		response.setHeader(RtspHeaderCode.Date, DateUtil.getGmtDate());
		response.setHeader(RtspHeaderCode.Location, request.getUrl().toString());
		response.setHeader(RtspHeaderCode.Session, sessionKey);
		session.write(response);

		logger.debug("vvs handle TEARDOWN request end } ");
	}

	/**
	 * 默认处理.
	 */
	private void onDefaultRequest(IoSession session, RtspRequest request, String cseq) {
		RtspResponse response = new RtspResponse();
		response.setCode(RtspCode.BadRequest);
		response.setHeader(RtspHeaderCode.CSeq, cseq);
		session.write(response);
	}

	/**
	 * 返回错误响应.
	 */
	private void handleError(IoSession session, String cseq, RtspCode code) {
		RtspResponse response = new RtspResponse();
		response.setCode(code);
		response.setHeader(RtspHeaderCode.CSeq, cseq);
		session.write(response);
	}

	/*-----------Setter And Getter --------------*/

	@Autowired
	public void setSessionAccessor(RtspSessionAccessor sessionAccessor) {
		this.sessionAccessor = sessionAccessor;
	}

	@Autowired
	public void setVvsIpAddress(String vvsIpAddress) {
		this.vvsIpAddress = vvsIpAddress;
	}

	@Autowired
	public void setPlayPort(int playPort) {
		this.playPort = playPort;
	}
}