package com.darkmi.vvs;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.darkmi.vvs.session.RtspSession;
import com.darkmi.vvs.session.RtspSessionAccessor;

import cn.com.supertv.srmserver.rtsp.RtspCode;
import cn.com.supertv.srmserver.rtsp.RtspHeaderCode;
import cn.com.supertv.srmserver.rtsp.RtspRequest;
import cn.com.supertv.srmserver.rtsp.RtspRequest.Verb;
import cn.com.supertv.srmserver.rtsp.RtspResponse;
import cn.com.supertv.srmserver.util.DateUtil;
import cn.com.supertv.srmserver.util.RtspUrl;

/**
 * 处理PLAY,PAUSE,GET_PARAMETER.
 */
@Component
public class PlayMessageHandler extends IoHandlerAdapter {
	private Logger logger = LoggerFactory.getLogger(PlayMessageHandler.class);
	private RtspSessionAccessor sessionAccessor;

	public static final String IO_SESSION_KEY = "io_session_key";
	private static final String CSEQ = "cseq";

	/**
	 * 接收到数据,进行处理.
	 */
	public void messageReceived(IoSession session, Object message) throws Exception {
		logger.debug("vvs 接收到客户端请求:\n{}", message);
		RtspRequest request = (RtspRequest) message;

		//保存cseq
		session.setAttribute(CSEQ, request.getHeader(RtspHeaderCode.CSeq));

		switch (request.getVerb()) {
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
			onDefaultRequest(session, request);
		}
	}

	/**
	 * 捕捉到异常,进行处理.
	 */
	public void exceptionCaught(IoSession session, Throwable cause) {
		logger.debug("vvs(8060) exceptionCaught begin { ");
		logger.error(cause.getMessage(), cause);

		//获取cseq
		String cseq = (String) session.getAttribute(CSEQ);
		if (null == cseq || "".equals(cseq)) {
			handleError(session, "0", RtspCode.InternalServerError);
		} else {
			handleError(session, cseq, RtspCode.InternalServerError);
		}

		//session.close(true);
		logger.debug("vvs(8060) exceptionCaught end } ");
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
			announceReq.setUrl(new RtspUrl("rtsp://192.168.14.220:8060/movie---26---bianfuxiaqianchuan"));
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
	private void onDefaultRequest(IoSession session, RtspRequest request) {
		RtspResponse response = new RtspResponse();
		response.setCode(RtspCode.BadRequest);
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
}
