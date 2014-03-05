package com.darkmi.server.rtsp;

import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.netty.handler.codec.rtsp.RtspResponseStatuses;
import io.netty.handler.codec.rtsp.RtspVersions;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.darkmi.server.core.RtspController;
import com.darkmi.server.session.RtspSession;
import com.darkmi.util.DateUtil;

/**
 * 
 * @author darkmi
 *
 */
public class TeardownAction implements Callable<HttpResponse> {
	private static Logger logger = LoggerFactory.getLogger(TeardownAction.class);
	private RtspController rtspController = null;
	private HttpRequest request = null;

	public TeardownAction(RtspController rtspController, HttpRequest request) {
		this.rtspController = rtspController;
		this.request = request;
	}

	public HttpResponse call() throws Exception {
		HttpResponse response = null;
		//		String sessionId = this.request.getHeader(RtspHeaders.Names.SESSION);
		//		if (sessionId == null) {
		//			response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.BAD_REQUEST);
		//			response.setHeader(HttpHeaders.Names.SERVER, RtspController.SERVER);
		//			response.setHeader(RtspHeaders.Names.CSEQ, this.request.getHeader(RtspHeaders.Names.CSEQ));
		//			logger.debug(response.toString());
		//			return response;
		//		} else {
		//			//获取cesq
		//			String cseq = request.getHeader(RtspHeaders.Names.CSEQ);
		//			if (null == cseq || "".equals(cseq)) {
		//				logger.error("cesq is null.");
		//				response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.INTERNAL_SERVER_ERROR);
		//				response.setHeader(HttpHeaders.Names.SERVER, RtspController.SERVER);
		//				response.setHeader(RtspHeaders.Names.CSEQ, this.request.getHeader(RtspHeaders.Names.CSEQ));
		//				return response;
		//			}
		//
		//			//获取sessionKey
		//			String sessionKey = request.getHeader(RtspHeaders.Names.SESSION);
		//			if (null == sessionKey || "".equals(sessionKey)) {
		//				logger.error("sessionKey is null.");
		//				response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.INTERNAL_SERVER_ERROR);
		//				response.setHeader(HttpHeaders.Names.SERVER, RtspController.SERVER);
		//				response.setHeader(RtspHeaders.Names.CSEQ, this.request.getHeader(RtspHeaders.Names.CSEQ));
		//				return response;
		//			}
		//
		//			//获取session
		//			RtspSession rtspSession = rtspController.getSessionAccessor().getSession(sessionKey, false);
		//			if (null == rtspSession) {
		//				logger.error("rtspSession is null.");
		//				response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.INTERNAL_SERVER_ERROR);
		//				response.setHeader(HttpHeaders.Names.SERVER, RtspController.SERVER);
		//				response.setHeader(RtspHeaders.Names.CSEQ, this.request.getHeader(RtspHeaders.Names.CSEQ));
		//				return response;
		//			}
		//
		//			//销毁session
		//			rtspSession.destroy();
		//
		//			//构建返回给请求方的响应
		//			response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.OK);
		//			response.setHeader(RtspHeaders.Names.CSEQ, cseq);
		//			response.setHeader(RtspHeaders.Names.DATE, DateUtil.getGmtDate());
		//			response.setHeader("Location", request.getUri().toCharArray());
		//			response.setHeader(RtspHeaders.Names.SESSION, sessionKey);
		return response;

	}
}
