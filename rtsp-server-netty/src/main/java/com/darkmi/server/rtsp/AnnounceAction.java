package com.darkmi.server.rtsp;

import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.netty.handler.codec.rtsp.RtspHeaders.Names;
import io.netty.handler.codec.rtsp.RtspMethods;
import io.netty.handler.codec.rtsp.RtspVersions;

import java.util.concurrent.Callable;

import com.darkmi.server.util.DateUtil;

public class AnnounceAction implements Callable<HttpRequest> {
	private HttpRequest originRequest = null;

	public AnnounceAction(HttpRequest request) {
		originRequest = request;
	}

	@Override
	public HttpRequest call() throws Exception {
		HttpRequest request = null;
		request = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.ANNOUNCE, originRequest.getUri());
		request.headers().set(Names.CSEQ, request.headers().get(Names.CSEQ));
		request.headers().set(RtspHeaders.Names.DATE, DateUtil.getGmtDate());
		request.headers().set(RtspHeaders.Names.SESSION, request.headers().get(Names.SESSION));
		request.headers().set("Notice", "1103 \"Stream Stalled\" event-date=20000406T091645Z");
		return request;
	}
}
