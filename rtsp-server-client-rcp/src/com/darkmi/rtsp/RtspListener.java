package com.darkmi.rtsp;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;


public interface RtspListener {
	public void onRtspRequest(HttpRequest request, Channel chanel);
	public void onRtspResponse(HttpResponse response);
	//public void onRtspResponse(DefaultHttpChunk response);
	//public void onRtspResponse(String response);
}
