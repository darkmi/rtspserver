package com.darkmi.server.rtsp;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.darkmi.server.core.RtspController;


/**
 * 
 * @author darkmi
 *
 */
public class AnnounceAction implements Callable<HttpRequest> {
	private static Logger logger = Logger.getLogger(PlayAction.class);
	@SuppressWarnings("unused")
	private RtspController rtspController = null;
	private HttpResponse response = null;

	public AnnounceAction(RtspController rtspController, HttpResponse response) {
		this.rtspController = rtspController;
		this.response = response;
	}

	@Override
	public HttpRequest call() throws Exception {
		HttpRequest request = null;
//		String uri = "rtsp://192.168.14.220:8060/movie---26---bianfuxiaqianchuan";
//		request = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.ANNOUNCE, uri);
//		request.setHeader(RtspHeaders.Names.CSEQ, "111");
//		request.setHeader(RtspHeaders.Names.DATE, null);
//		String sessionKey = response.getHeader(RtspHeaders.Names.SESSION);
//		request.setHeader(RtspHeaders.Names.SESSION, sessionKey);
//		request.setHeader("Notice", "1103 \"Stream Stalled\" event-date=20000406T091645Z");
//		logger.debug("setup request is --> " + request.toString());
		return request;
	}

}
