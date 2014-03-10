package com.darkmi.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.apache.log4j.Logger;

public class SetupHandler extends SimpleChannelInboundHandler<Object> {

	private static final Logger logger = Logger.getLogger(RtspClientHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		//messageReceived方法,名称很别扭，像是一个内部方法.		
		logger.info("client接收到视频服务器返回的消息:" + msg);
	}
}
