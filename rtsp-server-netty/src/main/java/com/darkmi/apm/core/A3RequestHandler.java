package com.darkmi.apm.core;

import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.HttpRequest;

public class A3RequestHandler extends SimpleChannelUpstreamHandler {
	Logger logger = Logger.getLogger(A3RequestHandler.class);
	private final A3ServerStackImpl a3ServerStackImpl;

	/**
	 * 构造函数.
	 * @param rtspServerStackImpl
	 */
	protected A3RequestHandler(A3ServerStackImpl rtspServerStackImpl) {
		this.a3ServerStackImpl = rtspServerStackImpl;
	}

	/**
	 * channel打开时将其添加到组中.
	 */
	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) {
		A3ServerStackImpl.allChannels.add(e.getChannel());
	}

	/**
	 * 接收到请求时的处理.
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		logger.debug("messageReceived { begin ...");
		Object o = e.getMessage();
		if (o instanceof ChannelBuffer) {
			ChannelBuffer c = (ChannelBuffer) o;
			byte[] b = new byte[c.capacity()];
			c.getBytes(0, b);
			logger.info("HTTP Request \n" + new String(b));
			return;
		}

		HttpRequest request = (HttpRequest) e.getMessage();
		logger.debug("request --> " + request);
		a3ServerStackImpl.processA3Request(request, e.getChannel());
		logger.debug("messageReceived ... end }");
	}

	/**
	 * 异常处理.
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		e.getCause().printStackTrace();
		e.getChannel().close();
	}
}
