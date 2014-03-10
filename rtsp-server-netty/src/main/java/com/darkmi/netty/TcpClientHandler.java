package com.darkmi.netty;

import org.apache.log4j.Logger;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TcpClientHandler extends SimpleChannelInboundHandler<Object> {
	private static final Logger logger = Logger.getLogger(TcpClientHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		//messageReceived方法,名称很别扭，像是一个内部方法.		
		logger.info("client接收到服务器返回的消息:" + msg);
	}
}
