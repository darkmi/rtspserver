package com.darkmi.rtsp;

import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.DefaultHttpChunk;
import org.jboss.netty.handler.codec.http.HttpResponse;

/**
 * RM响应处理.
 * @author Administrator
 *
 */
public class RMResponseHandler extends SimpleChannelUpstreamHandler {

	private final RMClientStackImpl rmClientStackImpl;
	private static Logger logger = Logger.getLogger(RMResponseHandler.class);

	public RMResponseHandler(RMClientStackImpl rmClientStackImpl) {
		this.rmClientStackImpl = rmClientStackImpl;
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		logger.debug("接收到SRM(554)的响应begin {....");
		Object o = e.getMessage();
		//logger.debug("rm response is --> " + o.toString());
		if (o instanceof DefaultHttpChunk) {
			DefaultHttpChunk dtc = (DefaultHttpChunk) o;
			ChannelBuffer c = dtc.getContent();
			byte[] b = new byte[c.capacity()];
			c.getBytes(0, b);
			logger.info("RM Response(DefaultHttpChunk) \n" + new String(b));
			return;
		}

		if (o instanceof ChannelBuffer) {
			ChannelBuffer c = (ChannelBuffer) o;
			byte[] b = new byte[c.capacity()];
			c.getBytes(0, b);
			logger.info("RM Response(ChannelBuffer) \n" + new String(b));
			return;
		}

		HttpResponse rtspResponse = (HttpResponse) e.getMessage();
		logger.debug("RM Response(HttpResponse) \n" + rtspResponse.toString());
		//处理响应
		rmClientStackImpl.processRtspResponse(rtspResponse);
		//关闭连接
		ctx.getChannel().close().awaitUninterruptibly();
		logger.debug("接收到SRM(554)的响应end ....}");

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		logger.debug(e.getCause());
	}
}
