package com.darkmi.server.core;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 
 * @author darkmi
 *
 */
public class RtspResponseHandler extends ChannelInboundHandlerAdapter {

	private final RtspClientStackImpl rtspClientStackImpl;

	public RtspResponseHandler(RtspClientStackImpl rtspClientStackImpl) {
		this.rtspClientStackImpl = rtspClientStackImpl;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		super.channelRead(ctx, msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
	}

//	@Override
//	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
//		HttpResponse rtspResponse = (HttpResponse) e.getMessage();
//		rtspClientStackImpl.processRtspResponse(rtspResponse);
//	}
	
	
}
