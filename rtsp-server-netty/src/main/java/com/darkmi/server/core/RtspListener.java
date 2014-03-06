package com.darkmi.server.core;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

/**
 * 
 * @author darkmi
 *
 */
public interface RtspListener {
	public void onRtspRequest(HttpRequest request, ChannelHandlerContext ctx);
	public void onRtspResponse(HttpResponse response);
}
