package com.darkmi.rtsp;

import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.http.HttpRequestEncoder;
import org.jboss.netty.handler.codec.http.HttpResponseDecoder;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.Timer;

public class SMPipelineFactory implements ChannelPipelineFactory {

	private final SMClientStackImpl smClientStackImpl;
	private final Timer timer;
	private final ChannelHandler idleStateHandler;
	private final ChannelHandler heartbeatHandler;

	public SMPipelineFactory(SMClientStackImpl smClientStackImpl, Timer t) {
		this.timer = t;
		this.smClientStackImpl = smClientStackImpl;
		this.idleStateHandler = new IdleStateHandler(timer, 0, 0, 10);
		this.heartbeatHandler = new HeartbeatHandler();
	}

	public ChannelPipeline getPipeline() throws Exception {
		//		// Create a default pipeline implementation.
		//		ChannelPipeline pipeline = pipeline();
		//		pipeline.addLast("timeout", this.idleStateHandler);
		//		pipeline.addLast("idleHandler", this.heartbeatHandler); // heartbeat 
		//		pipeline.addLast("decoder", new HttpResponseDecoder());
		//		pipeline.addLast("encoder", new HttpRequestEncoder());
		//		pipeline.addLast("handler", new SMResponseHandler(this.smClientStackImpl));
		//ChannelPipeline pipeline = Channels.pipeline(idleStateHandler,new SMResponseHandler(this.smClientStackImpl));

		ChannelPipeline pipeline = Channels.pipeline();
		pipeline.addLast("encoder", new HttpRequestEncoder());
		pipeline.addLast("decoder", new HttpResponseDecoder());
		//pipeline.addLast("aggregator", new HttpChunkAggregator(1048576));
		pipeline.addLast("timeout", idleStateHandler);
		pipeline.addLast("idleHandler", heartbeatHandler); // heartbeat
		pipeline.addLast("handler", new SMResponseHandler(smClientStackImpl));
		return pipeline;
	}
}