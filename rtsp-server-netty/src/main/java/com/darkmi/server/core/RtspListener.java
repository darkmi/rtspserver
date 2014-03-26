package com.darkmi.server.core;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

public interface RtspListener {
	public void onRtspRequest(HttpRequest request, Channel channel);
	public void onRtspResponse(HttpResponse response);
}
