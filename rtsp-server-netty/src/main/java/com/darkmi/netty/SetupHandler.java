package com.darkmi.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.DefaultLastHttpContent;
import io.netty.util.CharsetUtil;

import org.apache.log4j.Logger;

public class SetupHandler extends ChannelInboundHandlerAdapter {

	private static final Logger logger = Logger.getLogger(SetupHandler.class);

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.debug("接收到视频服务器返回的消息:" + msg);

		if (msg instanceof DefaultHttpResponse) {
			logger.debug("读取响应头....");
			DefaultHttpResponse response = (DefaultHttpResponse) msg;
			logger.debug("response header ==> \n" + response.toString());
		}

		if (msg instanceof DefaultLastHttpContent) {
			logger.debug("读取响应体....");
			DefaultLastHttpContent chunk = (DefaultLastHttpContent) msg;
			logger.debug("response content ==> \n" + chunk.content().toString(CharsetUtil.UTF_8));

			//logger.debug(chunk.content().toString(CharsetUtil.UTF_8));

			//if (chunk instanceof LastHttpContent) {
			//	logger.debug("读取LastHttpContent..............");
			//} else {
			//logger.info(chunk.content().toString(CharsetUtil.UTF_8));
			//}
		}
	}

}
