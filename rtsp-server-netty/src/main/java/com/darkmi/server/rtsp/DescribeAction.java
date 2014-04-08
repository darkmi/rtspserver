package com.darkmi.server.rtsp;

import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.netty.handler.codec.rtsp.RtspResponseStatuses;
import io.netty.handler.codec.rtsp.RtspVersions;

import java.util.concurrent.Callable;

import com.darkmi.server.core.RtspController;

public class DescribeAction implements Callable<HttpResponse> {
  private HttpRequest request = null;

  public DescribeAction(HttpRequest request) {
    this.request = request;
  }

  public HttpResponse call() throws Exception {
    HttpResponse response = null;
    response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.OK);
    response.headers().set(HttpHeaders.Names.SERVER, RtspController.SERVER);
    response.headers().set(RtspHeaders.Names.CSEQ,
        this.request.headers().get(RtspHeaders.Names.CSEQ));
    response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, "0");
    return response;
  }
}
