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

/**
 * 
 * @author darkmi
 *
 */
public class DescribeAction implements Callable<HttpResponse> {
	private static Logger logger = Logger.getLogger(DescribeAction.class);
	private HttpRequest request = null;
	public DescribeAction( HttpRequest request) {
		this.request = request;
	}

	public HttpResponse call() throws Exception {
		HttpResponse response = null;
		response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.OK);
		response.setHeader(HttpHeaders.Names.SERVER, RtspController.SERVER);
		response.setHeader(RtspHeaders.Names.CSEQ, this.request.getHeader(RtspHeaders.Names.CSEQ));
		response.setHeader(HttpHeaders.Names.CONTENT_LENGTH, "0");
		logger.debug(response);
		return response;
	}
}
