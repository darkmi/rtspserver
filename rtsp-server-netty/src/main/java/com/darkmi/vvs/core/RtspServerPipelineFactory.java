package com.darkmi.vvs.core;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;
import org.jboss.netty.handler.codec.rtsp.RtspRequestDecoder;

public class RtspServerPipelineFactory implements ChannelPipelineFactory {

	private final RtspServerStackImpl rtspServerStackImpl;

	protected RtspServerPipelineFactory(RtspServerStackImpl rtspServerStackImpl) {
		this.rtspServerStackImpl = rtspServerStackImpl;
	}

	public ChannelPipeline getPipeline() throws Exception {
		// Create a default pipeline implementation.
		ChannelPipeline pipeline = pipeline();
		pipeline.addLast("decoder", new RtspRequestDecoder());
		pipeline.addLast("encoder", new HttpResponseEncoder());
		pipeline.addLast("handler", new RtspRequestHandler(this.rtspServerStackImpl));
		return pipeline;
	}
}
