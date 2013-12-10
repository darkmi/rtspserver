package com.darkmi.server.rtsp;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.rtsp.RtspHeaders;
import org.jboss.netty.handler.codec.rtsp.RtspResponseStatuses;
import org.jboss.netty.handler.codec.rtsp.RtspVersions;

import com.darkmi.server.core.RtspController;
import com.darkmi.server.session.RtspSession;
import com.darkmi.util.DateUtil;

/**
 * 
 * @author darkmi
 *
 */
public class GetParameterAction implements Callable<HttpResponse> {
	private static Logger logger = Logger.getLogger(PlayAction.class);
	private RtspController rtspController = null;
	private HttpRequest request = null;

	public GetParameterAction(RtspController rtspController, HttpRequest request) {
		this.rtspController = rtspController;
		this.request = request;
	}

	public HttpResponse call() throws Exception {
		HttpResponse response = null;
		//获取cesq
		String cseq = request.getHeader(RtspHeaders.Names.CSEQ);
		if (null == cseq || "".equals(cseq)) {
			logger.error("cesq is null.");
			response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.INTERNAL_SERVER_ERROR);
			response.setHeader(HttpHeaders.Names.SERVER, RtspController.SERVER);
			response.setHeader(RtspHeaders.Names.CSEQ, this.request.getHeader(RtspHeaders.Names.CSEQ));
			return response;
		}

		String sessionId = this.request.getHeader(RtspHeaders.Names.SESSION);
		if (sessionId == null) {
			response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.BAD_REQUEST);
			response.setHeader(HttpHeaders.Names.SERVER, RtspController.SERVER);
			response.setHeader(RtspHeaders.Names.CSEQ, this.request.getHeader(RtspHeaders.Names.CSEQ));
			logger.debug(response.toString());
			return response;
		}

		//获取session
		RtspSession rtspSession = rtspController.getSessionAccessor().getSession(sessionId, false);
		if (null == rtspSession) {
			logger.error("rtspSession is null.");
			response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.BAD_REQUEST);
			response.setHeader(HttpHeaders.Names.SERVER, RtspController.SERVER);
			response.setHeader(RtspHeaders.Names.CSEQ, this.request.getHeader(RtspHeaders.Names.CSEQ));
			logger.debug(response.toString());
			return response;
		} else {
			//构造响应
			response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.OK);
			response.setHeader(RtspHeaders.Names.CSEQ, this.request.getHeader(RtspHeaders.Names.CSEQ));
			response.setHeader(RtspHeaders.Names.DATE, DateUtil.getGmtDate());
			response.setHeader(RtspHeaders.Names.SESSION, sessionId);

			//range处理
			String rangeValue = request.getHeader(RtspHeaders.Names.RANGE);
			if (null != rangeValue) {
				String[] rangeValues = rangeValue.split("=");
				response.setHeader(RtspHeaders.Names.RANGE, "npt=" + rangeValues[1] + "233.800");
			} else {
				response.setHeader(RtspHeaders.Names.RANGE, "npt=0.000-233.800");
			}
			//scale处理
			String scale = request.getHeader(RtspHeaders.Names.SCALE);
			if (null != scale) {
				response.setHeader(RtspHeaders.Names.SCALE, scale);
			} else {
				response.setHeader(RtspHeaders.Names.SCALE, "1.00");
			}
			return response;
		}

	}
}
