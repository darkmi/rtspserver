package com.darkmi.netty;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;

public class ClientIdleHandler extends IdleStateAwareChannelHandler {

	@Override
	public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e) throws Exception {
		if(e.getState() == IdleState.READER_IDLE){
			System.out.println("读空闲......");
		}else if(e.getState() == IdleState.WRITER_IDLE){
			System.out.println("写空闲......");
		}else if (e.getState() == IdleState.ALL_IDLE) {
			System.out.println("双端空闲......");
			//logger.debug("链路空闲！发送心跳！S:{} - C:{} idleState:{}", new Object[]{ctx.getChannel().getRemoteAddress(), ctx.getChannel().getLocalAddress() , e.getState()});
			//e.getChannel().write("aaa");
			super.channelIdle(ctx, e);
		}

	}
}
