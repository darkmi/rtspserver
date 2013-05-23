package com.darkmi.rtsp;

import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.HttpResponse;

/**
 * SM响应处理.
 * @author Administrator
 *
 */
public class SMResponseHandler extends SimpleChannelUpstreamHandler {

	private final SMClientStackImpl smClientStackImpl;
	private static Logger logger = Logger.getLogger(SMResponseHandler.class);

	public SMResponseHandler(SMClientStackImpl smClientStackImpl) {
		this.smClientStackImpl = smClientStackImpl;
	}

	@Override
	public void handleUpstream(ChannelHandlerContext arg0, ChannelEvent arg1) throws Exception {
		//logger.debug("handleUpstream{....");
		super.handleUpstream(arg0, arg1);
		//logger.debug("handleUpstream ....}");
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		logger.debug("接收到SM的响应begin {....");

		Object o = e.getMessage();
		//logger.debug("sm response is --> " + o.toString());
		if (o instanceof ChannelBuffer) {
			logger.debug("instanceof ChannelBuffer....");
			ChannelBuffer c = (ChannelBuffer) o;
			byte[] b = new byte[c.capacity()];
			c.getBytes(0, b);
			logger.info("ChannelBuffer \n" + new String(b));
			//smClientStackImpl.processRtspResponse(new String(b));
			//ctx.getChannel().close().awaitUninterruptibly();
			return;
		}

		if (o instanceof HttpResponse) {
			logger.debug("instanceof HttpResponse....");
			HttpResponse rtspResponse = (HttpResponse) e.getMessage();
			logger.debug("sm return : " + rtspResponse.toString());
			smClientStackImpl.processRtspResponse(rtspResponse);
			//ctx.getChannel().close().awaitUninterruptibly();
			return;
		}

		//不清楚为什么返回的是DefaultHttpChunk
		//		if (o instanceof DefaultHttpChunk) {
		//			logger.debug("instanceof DefaultHttpChunk....");
		//			DefaultHttpChunk dtc = (DefaultHttpChunk) o;
		//			ChannelBuffer c = dtc.getContent();
		//			byte[] b = new byte[c.capacity()];
		//			c.getBytes(0, b);
		//			logger.info("DefaultHttpChunk \n" + new String(b));
		//			//smClientStackImpl.processRtspResponse(new String(b));
		//			//ctx.getChannel().close().awaitUninterruptibly();
		//			return;
		//		}

		logger.debug("接收到SM的响应end ....}");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		logger.debug(e.getCause());
	}
}
