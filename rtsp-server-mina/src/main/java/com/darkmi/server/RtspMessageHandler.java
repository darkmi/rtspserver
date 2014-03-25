package com.darkmi.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.darkmi.server.rtsp.RtspCode;
import com.darkmi.server.rtsp.RtspHeaderCode;
import com.darkmi.server.rtsp.RtspRequest;
import com.darkmi.server.rtsp.RtspRequest.Verb;
import com.darkmi.server.rtsp.RtspResponse;
import com.darkmi.server.session.RtspSession;
import com.darkmi.server.session.RtspSessionAccessor;
import com.darkmi.server.session.RtspSessionKeyFactory;
import com.darkmi.server.session.SimpleRandomKeyFactory;
import com.darkmi.server.util.DateUtil;

@Component
public class RtspMessageHandler extends IoHandlerAdapter {
	private Logger logger = LoggerFactory.getLogger(RtspMessageHandler.class);
	private static final String CSEQ = "cseq";
	private static final String REQUIRE_VALUE_NGOD_R2 = "com.comcast.ngod.r2";
	private static final String REQUIRE_VALUE_NGOD_C1 = "com.comcast.ngod.c1";
	private static final String IO_SESSION_KEY = "io_session_key";
	private static final RtspSessionKeyFactory keyFactory = new SimpleRandomKeyFactory();
	private RtspSessionAccessor sessionAccessor;
	private String serverAddr;
	private int serverPort;

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		logger.debug("RTSP Server Receive Message: \n{}", message);
		RtspRequest request = (RtspRequest) message;
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
		default:
			onDefaultRequest(session, request, request.getHeader(RtspHeaderCode.CSeq));
		}
	}

	public void exceptionCaught(IoSession session, Throwable cause) {
		logger.error(cause.getMessage(), cause);
		String cseq = (String) session.getAttribute(CSEQ);
		if (null == cseq || "".equals(cseq)) {
			handleError(session, "0", RtspCode.InternalServerError);
		} else {
			handleError(session, cseq, RtspCode.InternalServerError);
		}
		//session.close(true);
	}

	private void onRequestOptions(IoSession session, RtspRequest request) {
		RtspResponse response = new RtspResponse();
		response.setHeader(RtspHeaderCode.Public, "DESCRIBE, SETUP, TEARDOWN");
		response.setHeader(RtspHeaderCode.Server, "RtspServer");
		response.setHeader(RtspHeaderCode.ContentLength, "0");
		session.write(response);
	}

	private void onRequestDescribe(IoSession session, RtspRequest request) {
		RtspResponse response = new RtspResponse();
		response.setHeader(RtspHeaderCode.Public, "DESCRIBE, SETUP, TEARDOWN, PLAY, PAUSE, GET_PARAMETER");
		response.setHeader(RtspHeaderCode.Server, "RtspServer");
		response.setHeader(RtspHeaderCode.ContentLength, "0");
		session.write(response);
	}

	private void onRequestSetup(IoSession session, RtspRequest request) {
		//get cesq
		String cseq = request.getHeader(RtspHeaderCode.CSeq);
		if (null == cseq || "".equals(cseq)) {
			logger.error("cesq is null...................");
			handleError(session, "0", RtspCode.HeaderFieldNotValidForResource);
			return;
		}

		//get require
		String requireValue = request.getHeader(RtspHeaderCode.Require);
		if (null == requireValue || "".equals(requireValue) || (!requireValue.equals(REQUIRE_VALUE_NGOD_R2))) {
			logger.error("require value ==> {} ", requireValue);
			handleError(session, "0", RtspCode.HeaderFieldNotValidForResource);
			return;
		}

		//get Transport
		String transport = request.getHeader(RtspHeaderCode.Transport);

		String sessionKey = keyFactory.createSessionKey();
		logger.debug("sessionKey --> " + sessionKey);
		RtspSession rtspSession = sessionAccessor.getSession(sessionKey, true);
		//save transport
		rtspSession.setAttribute(transport, "USED");

		RtspResponse response = new RtspResponse();
		response.setCode(RtspCode.OK);
		response.setHeader(RtspHeaderCode.CSeq, cseq);
		response.setHeader(RtspHeaderCode.Date, DateUtil.getGmtDate());
		response.setHeader(RtspHeaderCode.ContentType, "application/sdp");
		response.setHeader(RtspHeaderCode.Session, sessionKey + ";timeout=60");
		response.setHeader(RtspHeaderCode.OnDemandSessionId, request.getHeader(RtspHeaderCode.OnDemandSessionId));
		response.setHeader(RtspHeaderCode.Transport, request.getHeader(RtspHeaderCode.Transport));
		response.setHeader(RtspHeaderCode.Range, "npt=0-233.800");
		response.setHeader(RtspHeaderCode.Server, "RtspServer");
		response.setHeader("Method-Code", "SETUP");

		//set sdp extension
		StringBuffer sdp = new StringBuffer();
		sdp.append("v=0\r\n");
		sdp.append("o=- " + sessionKey + " 1339005446 IN IP4 " + serverAddr + "\r\n");
		sdp.append("s=RTSP Session\r\n");
		sdp.append("t=0 0\r\n");
		sdp.append("a=control:rtsp://" + serverAddr + ":" + serverPort + "/" + sessionKey + "\r\n");
		sdp.append("c=IN IP4 0.0.0.0\r\n");
		sdp.append("m=video 0 RTP/AVP 33\r\n");

		response.setHeader(RtspHeaderCode.ContentLength, String.valueOf(sdp.length()));
		response.setBuffer(sdp);

		session.write(response);
	}

	private void onRequestTeardown(IoSession session, RtspRequest request) {
		//get cesq
		String cseq = request.getHeader(RtspHeaderCode.CSeq);
		if (null == cseq || "".equals(cseq)) {
			logger.error("cesq is null...................");
			handleError(session, "0", RtspCode.HeaderFieldNotValidForResource);
			return;
		}

		//get require
		String requireValue = request.getHeader(RtspHeaderCode.Require);
		if (null == requireValue || "".equals(requireValue) || (!requireValue.equals(REQUIRE_VALUE_NGOD_R2))) {
			logger.error("require value ==> {} ", requireValue);
			handleError(session, "0", RtspCode.HeaderFieldNotValidForResource);
			return;
		}

		//get sessionKey
		String sessionKey = request.getHeader(RtspHeaderCode.Session);
		if (null == sessionKey || "".equals(sessionKey)) {
			logger.error("sessionKey is null...................");
			handleError(session, cseq, RtspCode.SessionNotFound);
			return;
		}

		//get session
		RtspSession rtspSession = sessionAccessor.getSession(sessionKey, false);
		if (null == rtspSession) {
			logger.error("rtspSession is null...................");
			handleError(session, cseq, RtspCode.SessionNotFound);
			return;
		}

		//destroy session
		rtspSession.destroy();

		RtspResponse response = new RtspResponse();
		response.setCode(RtspCode.OK);
		response.setHeader(RtspHeaderCode.CSeq, cseq);
		response.setHeader(RtspHeaderCode.Date, DateUtil.getGmtDate());
		response.setHeader(RtspHeaderCode.Location, request.getUrl().toString());
		response.setHeader(RtspHeaderCode.Session, sessionKey);
		response.setHeader("Method-Code", "TEARDOWN");
		session.write(response);
	}

	private void onRequestPlay(IoSession session, RtspRequest request) {
		//get cesq
		String cseq = request.getHeader(RtspHeaderCode.CSeq);
		if (null == cseq || "".equals(cseq)) {
			logger.error("cesq is null...................");
			handleError(session, "0", RtspCode.HeaderFieldNotValidForResource);
			return;
		}

		//get require
		String requireValue = request.getHeader(RtspHeaderCode.Require);
		if (null == requireValue || "".equals(requireValue) || (!requireValue.equals(REQUIRE_VALUE_NGOD_C1))) {
			logger.error("require value ==> {} ", requireValue);
			handleError(session, "0", RtspCode.HeaderFieldNotValidForResource);
			return;
		}

		//getsessionKey
		String sessionKey = request.getHeader(RtspHeaderCode.Session);
		if (null == sessionKey || "".equals(sessionKey)) {
			logger.error("sessionKey is null...................");
			handleError(session, cseq, RtspCode.SessionNotFound);
		}

		//get session
		RtspSession rtspSession = sessionAccessor.getSession(sessionKey, false);
		if (null == rtspSession) {
			logger.error("rtspSession is null...................");
			handleError(session, cseq, RtspCode.SessionNotFound);
		} else {
			rtspSession.setAttribute(IO_SESSION_KEY, session);
			RtspResponse response = new RtspResponse();
			response.setCode(RtspCode.OK);
			response.setHeader(RtspHeaderCode.CSeq, cseq);
			response.setHeader(RtspHeaderCode.Date, DateUtil.getGmtDate());
			response.setHeader(RtspHeaderCode.Session, sessionKey);

			String rangeValue = request.getHeader("Range");
			if (null != rangeValue) {
				String[] rangeValues = rangeValue.split("=");
				response.setHeader(RtspHeaderCode.Range, "npt=" + rangeValues[1] + "233.800");
			} else {
				response.setHeader(RtspHeaderCode.Range, "npt=0.000-233.800");
			}

			String scale = request.getHeader(RtspHeaderCode.Scale);
			if (null != scale) {
				response.setHeader(RtspHeaderCode.Scale, scale);
			} else {
				response.setHeader(RtspHeaderCode.Scale, "1.00");
			}
			session.write(response);
		}
	}

	private void onRequestPause(IoSession session, RtspRequest request) {
		//get cesq
		String cseq = request.getHeader(RtspHeaderCode.CSeq);
		if (null == cseq || "".equals(cseq)) {
			logger.error("cesq is null...................");
			handleError(session, "0", RtspCode.HeaderFieldNotValidForResource);
			return;
		}

		//get require
		String requireValue = request.getHeader(RtspHeaderCode.Require);
		if (null == requireValue || "".equals(requireValue) || (!requireValue.equals(REQUIRE_VALUE_NGOD_C1))) {
			logger.error("require value ==> {} ", requireValue);
			handleError(session, "0", RtspCode.HeaderFieldNotValidForResource);
			return;
		}

		//get sessionKey
		String sessionKey = request.getHeader(RtspHeaderCode.Session);
		if (null == sessionKey || "".equals(sessionKey)) {
			logger.error("sessionKey is null...................");
			handleError(session, cseq, RtspCode.SessionNotFound);
		}

		//get session
		RtspSession rtspSession = sessionAccessor.getSession(sessionKey, false);
		if (null == rtspSession) {
			logger.error("rtspSession is null...................");
			handleError(session, cseq, RtspCode.SessionNotFound);
		} else {

			rtspSession.setAttribute(IO_SESSION_KEY, session);

			RtspResponse response = new RtspResponse();
			response.setCode(RtspCode.OK);
			response.setHeader(RtspHeaderCode.CSeq, cseq);
			response.setHeader(RtspHeaderCode.Require, "HFC.Delivery.Profile.1.0");
			response.setHeader(RtspHeaderCode.Date, DateUtil.getGmtDate());
			response.setHeader(RtspHeaderCode.Session, sessionKey);
			response.setHeader(RtspHeaderCode.Scale, "1.00");

			String rangeValue = request.getHeader("Range");
			if (null != rangeValue) {
				String[] rangeValues = rangeValue.split("=");
				response.setHeader(RtspHeaderCode.Range, "npt=" + rangeValues[1] + "233.800");
			} else {
				response.setHeader(RtspHeaderCode.Range, "npt=0.000-233.800");
			}

			session.write(response);

			//send ANNOUNCE
			RtspRequest announceReq = new RtspRequest();
			announceReq.setVerb(Verb.ANNOUNCE);
			announceReq.setUrl("rtsp://192.168.14.220:8060/" + sessionKey);
			announceReq.setHeader(RtspHeaderCode.CSeq, cseq);
			announceReq.setHeader(RtspHeaderCode.Date, DateUtil.getGmtDate());
			announceReq.setHeader(RtspHeaderCode.Session, sessionKey);
			announceReq.setHeader(RtspHeaderCode.Notice, "1103 \"Stream Stalled\" event-date=20000406T091645Z");
			session.write(announceReq);
		}
	}

	private void onRequestGP(IoSession session, RtspRequest request) {
		//get cesq
		String cseq = request.getHeader(RtspHeaderCode.CSeq);
		if (null == cseq || "".equals(cseq)) {
			logger.error("cesq is null...................");
			handleError(session, "0", RtspCode.HeaderFieldNotValidForResource);
			return;
		}

		//get require
		String requireValue = request.getHeader(RtspHeaderCode.Require);
		if (null == requireValue || "".equals(requireValue) || (!requireValue.equals(REQUIRE_VALUE_NGOD_C1))) {
			logger.error("require value ==> {} ", requireValue);
			handleError(session, "0", RtspCode.HeaderFieldNotValidForResource);
			return;
		}

		//get sessionKey
		String sessionKey = request.getHeader(RtspHeaderCode.Session);
		if (null == sessionKey || "".equals(sessionKey)) {
			logger.debug("sessionKey is null...................");
			handleError(session, cseq, RtspCode.SessionNotFound);
		}

		//get session
		RtspSession rtspSession = sessionAccessor.getSession(sessionKey, false);
		if (null == rtspSession) {
			handleError(session, cseq, RtspCode.SessionNotFound);
		} else {
			//save IO_SESSION_ID
			rtspSession.setAttribute(IO_SESSION_KEY, session);
			
			//sdp
			StringBuffer sdp = new StringBuffer();
			sdp.append("position: 22\r\n");
			sdp.append("presentation state: play\r\n\r\n");
			sdp.append("scale: 1\r\n");

			RtspResponse response = new RtspResponse();
			response.setCode(RtspCode.OK);
			response.setHeader(RtspHeaderCode.CSeq, cseq);
			response.setHeader(RtspHeaderCode.Date, DateUtil.getGmtDate());
			response.setHeader(RtspHeaderCode.Session, sessionKey);
			response.setHeader(RtspHeaderCode.ContentLength, String.valueOf(sdp.length()));
			response.appendToBuffer(sdp);
			session.write(response);
		}
	}

	private void onDefaultRequest(IoSession session, RtspRequest request, String cseq) {
		RtspResponse response = new RtspResponse();
		response.setCode(RtspCode.BadRequest);
		response.setHeader(RtspHeaderCode.CSeq, cseq);
		session.write(response);
	}

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

	public String getServerAddr() {
		return serverAddr;
	}

	public void setServerAddr(String serverAddr) {
		this.serverAddr = serverAddr;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
}