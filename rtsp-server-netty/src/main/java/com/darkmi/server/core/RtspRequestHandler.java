package com.darkmi.server.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import org.apache.log4j.Logger;

/**
 * 
 * @author darkmi
 *
 */
public class RtspRequestHandler extends ChannelInboundHandlerAdapter {
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
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//super.channelRead(ctx, msg);
		try {
			if (msg instanceof ByteBuf) {
				ByteBuf byteBuf = (ByteBuf) msg;
				byte[] b = new byte[byteBuf.capacity()];
				byteBuf.getBytes(0, b);
				logger.info("RTSP Request \n" + new String(b));
				
				
			}
		} finally {
			 ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
	}

	//	@Override
	//	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) {
	//		RtspServerStackImpl.allChannels.add(e.getChannel());
	//	}

	//@Override
	//public void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
	//Object o = e.getMessage();
	//if (msg instanceof ChannelBuffer) {
	//	ChannelBuffer c = (ChannelBuffer) o;
	//	byte[] b = new byte[c.capacity()];
	//	c.getBytes(0, b);
	//	logger.info("RTSP Request \n" + new String(b));
	//	return;
	//}

	//HttpRequest rtspRequest = (HttpRequest) e.getMessage();
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
	//rtspServerStackImpl.processRtspRequest(rtspRequest, e.getChannel());
	//}

	//@Override
	//public void exceptionCaught(ChannelHandlerContext ctx, Object msg)  {
	//	e.getCause().printStackTrace();
	//	e.getChannel().close();
	//}
}
