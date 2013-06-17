package com.darkmi.server.core;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.HttpResponse;

public class RtspResponseHandler extends SimpleChannelUpstreamHandler {

	private final RtspClientStackImpl rtspClientStackImpl;

	public RtspResponseHandler(RtspClientStackImpl rtspClientStackImpl) {
		this.rtspClientStackImpl = rtspClientStackImpl;
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		HttpResponse rtspResponse = (HttpResponse) e.getMessage();
		rtspClientStackImpl.processRtspResponse(rtspResponse);
	}
}
