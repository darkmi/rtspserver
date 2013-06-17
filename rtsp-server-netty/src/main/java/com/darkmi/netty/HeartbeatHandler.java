package com.darkmi.netty;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;

/**
 * 心跳处理器.
 * @author Administrator
 *
 */
public class HeartbeatHandler extends IdleStateAwareChannelHandler {
	private static Logger logger = Logger.getLogger(HeartbeatHandler.class);

	@Override
	public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e) {
		logger.debug("空闲.......");
		System.out.println("空闲.......");
		if (e.getState() == IdleState.READER_IDLE) {
			logger.debug("读空闲.............");
		} else if (e.getState() == IdleState.WRITER_IDLE) {
			logger.debug("写空闲.............");
		} else if (e.getState() == IdleState.ALL_IDLE) {
			logger.debug("双端空闲.............");
		}
	}

}
