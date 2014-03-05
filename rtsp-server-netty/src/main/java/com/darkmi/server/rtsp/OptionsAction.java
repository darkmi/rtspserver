package com.darkmi.server.rtsp;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

import java.util.concurrent.Callable;

/**
 * 
 * @author darkmi
 *
 */
public class OptionsAction implements Callable<HttpResponse> {
	private HttpRequest request = null;
//	public final static String OPTIONS = RtspMethods.DESCRIBE.getName() + ", " + RtspMethods.SETUP.getName() + ", "
//			+ RtspMethods.TEARDOWN.getName() + ", " + RtspMethods.PLAY.getName() + ", " + RtspMethods.OPTIONS.getName();

	public OptionsAction(HttpRequest request) {
		this.request = request;
	}

	public HttpResponse call() throws Exception {
		HttpResponse response = null;
//		response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.OK);
//		response.setHeader(HttpHeaders.Names.SERVER, RtspController.SERVER);
//		response.setHeader(RtspHeaders.Names.CSEQ, this.request.getHeader(RtspHeaders.Names.CSEQ));
//		response.setHeader(RtspHeaders.Names.PUBLIC, OPTIONS);
		return response;
	}
}
