package com.darkmi.netty;

import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.Timer;

public class MyPipelineFactory implements ChannelPipelineFactory {
	private final Timer timer;
	private static final ChannelHandler stringDecoder = new StringDecoder();
	private static final ChannelHandler stringEncoder = new StringEncoder();
	private final ChannelHandler idleStateHandler;
	private final ChannelHandler heartbeatHandler;

	public MyPipelineFactory(Timer t) {
		this.timer = t;
		this.idleStateHandler = new IdleStateHandler(timer, 5, 5, 5);
		this.heartbeatHandler = new HeartbeatHandler();
	}

	public ChannelPipeline getPipeline() {
		// create default pipeline from static method     
		ChannelPipeline pipeline = Channels.pipeline();
		pipeline.addLast("idleStateHandler", this.idleStateHandler);
		// heartbeat   
		pipeline.addLast("heartbeatHandler", this.heartbeatHandler);
		pipeline.addLast("framer", new DelimiterBasedFrameDecoder(1024, Delimiters.lineDelimiter()));
		pipeline.addLast("stringDecoder", stringDecoder);
		pipeline.addLast("stringEncoder", stringEncoder);
		pipeline.addLast("ServerHandler", new DiscardServerHandler());
		return pipeline;
	}

}
