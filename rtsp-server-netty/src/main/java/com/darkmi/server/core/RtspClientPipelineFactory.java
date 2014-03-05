package com.darkmi.server.core;

import io.netty.channel.ChannelPipeline;

/**
 * 
 * @author darkmi
 *
 */
public class RtspClientPipelineFactory {

	private final RtspClientStackImpl rtspClientStackImpl;

	public RtspClientPipelineFactory(RtspClientStackImpl rtspClientStackImpl) {
		this.rtspClientStackImpl = rtspClientStackImpl;
	}

	public ChannelPipeline getPipeline() throws Exception {
		return null;
//		// Create a default pipeline implementation.
//		ChannelPipeline pipeline = pipeline();
//		pipeline.addLast("decoder", new HttpResponseDecoder());
//		pipeline.addLast("encoder", new HttpRequestEncoder());
//		pipeline.addLast("handler", new RtspResponseHandler(this.rtspClientStackImpl));
//		return pipeline;
	}

}
