package com.darkmi.server.rtsp;

import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.netty.handler.codec.rtsp.RtspResponseStatuses;
import io.netty.handler.codec.rtsp.RtspVersions;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

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
//		response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.OK);
//		response.setHeader(HttpHeaders.Names.SERVER, RtspController.SERVER);
//		response.setHeader(RtspHeaders.Names.CSEQ, this.request.getHeader(RtspHeaders.Names.CSEQ));
//		response.setHeader(HttpHeaders.Names.CONTENT_LENGTH, "0");
//		logger.debug(response);
		return response;
	}
}
