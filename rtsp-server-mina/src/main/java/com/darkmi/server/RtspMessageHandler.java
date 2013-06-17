package com.darkmi.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.darkmi.server.rtsp.RtspCode;
import com.darkmi.server.rtsp.RtspHeaderCode;
import com.darkmi.server.rtsp.RtspRequest;
import com.darkmi.server.rtsp.RtspResponse;
import com.darkmi.server.rtsp.RtspUrl;
import com.darkmi.server.rtsp.RtspRequest.Verb;
import com.darkmi.server.session.RtspSession;
import com.darkmi.server.session.RtspSessionAccessor;
import com.darkmi.server.session.RtspSessionKeyFactory;
import com.darkmi.server.session.SimpleRandomKeyFactory;
import com.darkmi.server.util.DateUtil;

@Component
public class RtspMessageHandler extends IoHandlerAdapter {
	private Logger logger = LoggerFactory.getLogger(RtspMessageHandler.class);
	private static final String CSEQ = "cseq";
	private static final String REQUIRE_VALUE_HFC = "HFC.Delivery.Profile.1.0";
	private static final String REQUIRE_VALUE_NGOD_R2 = "com.comcast.ngod.r2";
	public static final String IO_SESSION_KEY = "io_session_key";
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
		case PLAY:
			onRequestPlay(session, request);
			break;
		case PAUSE:
			onRequestPause(session, request);
			break;
		case GET_PARAMETER:
			onRequestGP(session, request);
			break;
		case ANNOUNCE:
			onRequestAnnounce(request);
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
		RtspResponse response = new RtspResponse();
		response.setHeader(RtspHeaderCode.Public, "DESCRIBE, SETUP, TEARDOWN");
		response.setHeader(RtspHeaderCode.Server, "MediaHawk");
		response.setHeader(RtspHeaderCode.ContentLength, "0");
		session.write(response);
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

		//获取destination
		String destination = "";
		if (null == destination || "".equals(destination)) {
			logger.error("destination is null.");
			handleError(session, cseq, RtspCode.UnsupportedTransport);
			return;
		}
		//获取port
		int port = 1;

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
			response.setHeader(RtspHeaderCode.Transport, "");
			response.setHeader(RtspHeaderCode.Range, "npt=0-233.800");
			String location = "rtsp://" + vvsIpAddress + ":" + playPort + "/";
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
			response.setHeader(RtspHeaderCode.Transport, "");
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
	 * 处理PLAY命令.
	 */
	private void onRequestPlay(IoSession session, RtspRequest request) {
		logger.debug("vvs handle PLAY request begin { ");
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
		}

		//获取session
		RtspSession rtspSession = sessionAccessor.getSession(sessionKey, false);
		if (null == rtspSession) {
			logger.error("rtspSession is null.");
			handleError(session, cseq, RtspCode.SessionNotFound);
		} else {
			//保存IO_SESSION_ID
			rtspSession.setAttribute(IO_SESSION_KEY, session);
			//构造响应
			RtspResponse response = new RtspResponse();
			response.setCode(RtspCode.OK);
			response.setHeader(RtspHeaderCode.CSeq, cseq);
			response.setHeader(RtspHeaderCode.Date, DateUtil.getGmtDate());
			response.setHeader(RtspHeaderCode.Session, sessionKey);
			//range处理
			String rangeValue = request.getHeader("Range");
			if (null != rangeValue) {
				String[] rangeValues = rangeValue.split("=");
				response.setHeader(RtspHeaderCode.Range, "npt=" + rangeValues[1] + "233.800");
			} else {
				response.setHeader(RtspHeaderCode.Range, "npt=0.000-233.800");
			}
			//scale处理
			String scale = request.getHeader(RtspHeaderCode.Scale);
			if (null != scale) {
				response.setHeader(RtspHeaderCode.Scale, scale);
			} else {
				response.setHeader(RtspHeaderCode.Scale, "1.00");
			}
			session.write(response);
		}
		logger.debug("vvs handle PLAY request end } ");
	}

	/**
	 * 处理PAUSE命令.
	 */
	private void onRequestPause(IoSession session, RtspRequest request) {
		logger.debug("vvs handle PAUSE request begin { ");
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
		}

		//获取session
		RtspSession rtspSession = sessionAccessor.getSession(sessionKey, false);
		if (null == rtspSession) {
			logger.error("rtspSession is null.");
			handleError(session, cseq, RtspCode.SessionNotFound);
		} else {
			//保存IO_SESSION_ID
			rtspSession.setAttribute(IO_SESSION_KEY, session);
			//构造响应
			RtspResponse response = new RtspResponse();
			response.setCode(RtspCode.OK);
			response.setHeader(RtspHeaderCode.CSeq, cseq);
			response.setHeader(RtspHeaderCode.Require, "HFC.Delivery.Profile.1.0");
			response.setHeader(RtspHeaderCode.Date, DateUtil.getGmtDate());
			response.setHeader(RtspHeaderCode.Session, sessionKey);
			response.setHeader(RtspHeaderCode.Scale, "1.00");
			//range处理
			String rangeValue = request.getHeader("Range");
			if (null != rangeValue) {
				String[] rangeValues = rangeValue.split("=");
				response.setHeader(RtspHeaderCode.Range, "npt=" + rangeValues[1] + "233.800");
			} else {
				response.setHeader(RtspHeaderCode.Range, "npt=0.000-233.800");
			}

			session.write(response);

			//暂停5毫秒再发送ANNOUNCE
			//try {
			//	Thread.sleep(5);
			//} catch (InterruptedException e) {
			//		logger.error(e.getMessage());
			//}

			//发送暂停ANNOUNCE
			RtspRequest announceReq = new RtspRequest();
			announceReq.setVerb(Verb.ANNOUNCE);
			announceReq.setUrl(new RtspUrl("rtsp://192.168.14.220:8060/movie---26---bianfuxiaqianchuan").toString());
			announceReq.setHeader(RtspHeaderCode.CSeq, cseq);
			announceReq.setHeader(RtspHeaderCode.Date, DateUtil.getGmtDate());
			announceReq.setHeader(RtspHeaderCode.Session, sessionKey);
			announceReq.setHeader(RtspHeaderCode.Notice, "1103 \"Stream Stalled\" event-date=20000406T091645Z");
			session.write(announceReq);
		}
		logger.debug("vvs handle PAUSE request end } ");
	}

	/**
	 * 处理GET_PARAMETER命令.
	 */
	private void onRequestGP(IoSession session, RtspRequest request) {
		logger.debug("vvs handle GET_PARAMETER request begin { ");
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
			logger.debug("sessionKey is null.");
			handleError(session, cseq, RtspCode.SessionNotFound);
		}

		//获取session
		RtspSession rtspSession = sessionAccessor.getSession(sessionKey, false);
		if (null == rtspSession) {
			handleError(session, cseq, RtspCode.SessionNotFound);
		} else {
			//保存IO_SESSION_ID
			rtspSession.setAttribute(IO_SESSION_KEY, session);
			//构造响应
			RtspResponse response = new RtspResponse();
			response.setCode(RtspCode.OK);
			response.setHeader(RtspHeaderCode.CSeq, cseq);
			response.setHeader(RtspHeaderCode.Date, DateUtil.getGmtDate());
			response.setHeader(RtspHeaderCode.Session, sessionKey);
			session.write(response);
		}
		logger.debug("vvs handle GET_PARAMETER request end } ");
	}

	/**
	 * 处理ANNOUNCE命令.
	 */
	private void onRequestAnnounce(RtspRequest request) {

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

	//	@Autowired
	//	public void setSessionAccessor(RtspSessionAccessor sessionAccessor) {
	//		this.sessionAccessor = sessionAccessor;
	//	}
	//
	//	@Autowired
	//	public void setVvsIpAddress(String vvsIpAddress) {
	//		this.vvsIpAddress = vvsIpAddress;
	//	}
	//
	//	@Autowired
	//	public void setPlayPort(int playPort) {
	//		this.playPort = playPort;
	//	}
}