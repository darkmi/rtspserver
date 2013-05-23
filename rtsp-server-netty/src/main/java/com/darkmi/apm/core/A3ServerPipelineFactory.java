package com.darkmi.apm.core;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;

public class A3ServerPipelineFactory implements ChannelPipelineFactory {

	private final A3ServerStackImpl a3ServerStackImpl;

	protected A3ServerPipelineFactory(A3ServerStackImpl a3ServerStackImpl) {
		this.a3ServerStackImpl = a3ServerStackImpl;
	}

	public ChannelPipeline getPipeline() throws Exception {
		// Create a default pipeline implementation.
		ChannelPipeline pipeline = pipeline();
		pipeline.addLast("decoder", new HttpRequestDecoder());
		pipeline.addLast("encoder", new HttpResponseEncoder());
		pipeline.addLast("handler", new A3RequestHandler(this.a3ServerStackImpl));
		return pipeline;
	}
}
