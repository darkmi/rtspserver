package com.darkmi.server.core;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.util.CharsetUtil;

import org.apache.log4j.Logger;

public class RtspRequestHandler extends ChannelInboundHandlerAdapter {
	Logger logger = Logger.getLogger(RtspRequestHandler.class);
	private final RtspServerStackImpl rtspServerStackImpl;

	protected RtspRequestHandler(RtspServerStackImpl rtspServerStackImpl) {
		this.rtspServerStackImpl = rtspServerStackImpl;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.debug("clent send ========> \n" + msg);
		if (msg instanceof DefaultFullHttpRequest) {
			DefaultFullHttpRequest request = (DefaultFullHttpRequest) msg;
			logger.debug("client request header ========> \n" + request);
			logger.debug("client request content ========> \n " + request.content().toString(CharsetUtil.UTF_8));
			rtspServerStackImpl.processRtspRequest(request, ctx.channel());
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.debug("exceptionCaught...............");
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.debug("channelActive...............");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.debug("channelInactive...............");
	}

}
