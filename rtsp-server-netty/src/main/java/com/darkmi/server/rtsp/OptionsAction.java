package com.darkmi.server.rtsp;

import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.netty.handler.codec.rtsp.RtspResponseStatuses;
import io.netty.handler.codec.rtsp.RtspVersions;

import java.util.concurrent.Callable;

/**
 * 
 * @author darkmi
 *
 */
public class OptionsAction implements Callable<HttpResponse> {
	private HttpRequest request = null;

	public OptionsAction(HttpRequest request) {
		this.request = request;
	}

	public HttpResponse call() throws Exception {
		HttpResponse response = null;
		response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.OK);
		response.headers().add(RtspHeaders.Names.SERVER, "RtspServer");
		response.headers().add(RtspHeaders.Names.CSEQ, this.request.headers().get(RtspHeaders.Names.CSEQ));
		response.headers().add(RtspHeaders.Names.PUBLIC, "SETUP,PLAY,PAUSE,TEARDOWN");
		return response;
	}
}
