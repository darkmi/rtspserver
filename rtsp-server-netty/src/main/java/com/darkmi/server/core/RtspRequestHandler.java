package com.darkmi.server.core;

import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.HttpRequest;

public class RtspRequestHandler extends SimpleChannelUpstreamHandler {
	Logger logger = Logger.getLogger(RtspRequestHandler.class);
	private final RtspServerStackImpl rtspServerStackImpl;

	/**
	 * 构造函数.
	 * @param rtspServerStackImpl
	 */
	protected RtspRequestHandler(RtspServerStackImpl rtspServerStackImpl) {
		this.rtspServerStackImpl = rtspServerStackImpl;
	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) {
		RtspServerStackImpl.allChannels.add(e.getChannel());
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		Object o = e.getMessage();
		if (o instanceof ChannelBuffer) {
			ChannelBuffer c = (ChannelBuffer) o;
			byte[] b = new byte[c.capacity()];
			c.getBytes(0, b);
			logger.info("RTSP Request \n" + new String(b));
			return;
		}

		HttpRequest rtspRequest = (HttpRequest) e.getMessage();
		//		if (rtspRequest.getMethod().equals(HttpMethod.POST)) {
		//			if (logger.isInfoEnabled()) {
		//				logger.info("Received the POST Request. Changing the PipeLine");
		//			}
		//
		//			ChannelPipeline p = ctx.getChannel().getPipeline();
		//			// p.replace("decoder", "frameDecoder",
		//			// new DelimiterBasedFrameDecoder(80, Delimiters
		//			// .nulDelimiter()));
		//			p.replace("decoder", "base64Decoder", new Base64Decoder());
		//		}
		rtspServerStackImpl.processRtspRequest(rtspRequest, e.getChannel());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		e.getCause().printStackTrace();
		e.getChannel().close();
	}
}
