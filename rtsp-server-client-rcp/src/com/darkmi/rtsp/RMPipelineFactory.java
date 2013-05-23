package com.darkmi.rtsp;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.http.HttpRequestEncoder;
import org.jboss.netty.handler.codec.http.HttpResponseDecoder;

public class RMPipelineFactory implements ChannelPipelineFactory {

	private final RMClientStackImpl rmClientStackImpl;

	public RMPipelineFactory(RMClientStackImpl rmClientStackImpl) {
		this.rmClientStackImpl = rmClientStackImpl;
	}

	public ChannelPipeline getPipeline() throws Exception {
		// Create a default pipeline implementation.
		ChannelPipeline pipeline = pipeline();
		pipeline.addLast("decoder", new HttpResponseDecoder());
		//pipeline.addLast("aggregator", new HttpChunkAggregator(1048576));
		pipeline.addLast("encoder", new HttpRequestEncoder());
		pipeline.addLast("handler", new RMResponseHandler(this.rmClientStackImpl));
		return pipeline;
	}
}