package com.darkmi.server.core;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.util.CharsetUtil;

import org.apache.log4j.Logger;

public class RtspResponseHandler extends ChannelInboundHandlerAdapter {
	private static Logger logger = Logger.getLogger(RtspResponseHandler.class);
	private final RtspClientStackImpl rtspClientStackImpl;

	public RtspResponseHandler(RtspClientStackImpl rtspClientStackImpl) {
		this.rtspClientStackImpl = rtspClientStackImpl;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof DefaultFullHttpResponse) {
			DefaultFullHttpResponse response = (DefaultFullHttpResponse) msg;
			logger.debug(response);
			logger.debug(response.content().toString(CharsetUtil.UTF_8));
			rtspClientStackImpl.processRtspResponse(response);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
	}
}
