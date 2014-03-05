package com.darkmi.server.core;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

/**
 * 
 * @author darkmi
 *
 */
public interface RtspListener {
	public void onRtspRequest(HttpRequest request, Channel chanel);
	public void onRtspResponse(HttpResponse response);
}
