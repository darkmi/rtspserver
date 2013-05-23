package com.darkmi.vvs.rtsp;

import java.util.concurrent.Callable;

import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.rtsp.RtspHeaders;
import org.jboss.netty.handler.codec.rtsp.RtspMethods;
import org.jboss.netty.handler.codec.rtsp.RtspResponseStatuses;
import org.jboss.netty.handler.codec.rtsp.RtspVersions;

import com.darkmi.vvs.core.RtspController;


/**
 * 处理Option请求.
 * @author MiXiaohui
 *
 */
public class OptionsAction implements Callable<HttpResponse> {
	private HttpRequest request = null;
	public final static String OPTIONS = RtspMethods.DESCRIBE.getName() + ", " + RtspMethods.SETUP.getName() + ", "
			+ RtspMethods.TEARDOWN.getName() + ", " + RtspMethods.PLAY.getName() + ", " + RtspMethods.OPTIONS.getName();

	public OptionsAction(HttpRequest request) {
		this.request = request;
	}

	public HttpResponse call() throws Exception {
		HttpResponse response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.OK);
		response.setHeader(HttpHeaders.Names.SERVER, RtspController.SERVER);
		response.setHeader(RtspHeaders.Names.CSEQ, this.request.getHeader(RtspHeaders.Names.CSEQ));
		response.setHeader(RtspHeaders.Names.PUBLIC, OPTIONS);
		return response;
	}
}
