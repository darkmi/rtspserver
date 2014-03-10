package com.darkmi.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.log4j.Logger;

public class TcpServerHandler extends SimpleChannelInboundHandler<Object> {
	private static final Logger logger = Logger.getLogger(TcpServerHandler.class);

	@Override
	public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("SERVER接收到消息:" + msg);
		ctx.channel().writeAndFlush("yes, server is accepted you ,nice !" + msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.warn("Unexpected exception from downstream.", cause);
		ctx.close();
	}
	
	
}
