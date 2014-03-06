package com.darkmi.server.core;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultHttpRequest;

import org.apache.log4j.Logger;

/**
 * 
 * @author darkmi
 *
 */
public class RtspRequestHandler extends ChannelInboundHandlerAdapter {
	Logger logger = Logger.getLogger(RtspRequestHandler.class);
	private final RtspServerStackImpl rtspServerStackImpl;

	protected RtspRequestHandler(RtspServerStackImpl rtspServerStackImpl) {
		this.rtspServerStackImpl = rtspServerStackImpl;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.debug("msg 接收到客户端请求 ========> " + msg);
		if (msg instanceof DefaultHttpRequest) {
			DefaultHttpRequest rtspMessage = (DefaultHttpRequest) msg;
			logger.debug("rtspMessage 接收到客户端请求 ========> " + rtspMessage);
			rtspServerStackImpl.processRtspRequest(rtspMessage, ctx);
		} else {
			logger.debug("读取HTTP/RTSP请求的Content...........");
		}

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.debug("exceptionCaught");
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.debug("channelActive");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.debug("channelInactive");
	}

}
