package com.darkmi.server.core;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.rtsp.RtspRequestDecoder;
import io.netty.handler.codec.rtsp.RtspResponseEncoder;

/**
 * 
 * @author darkmi
 *
 */
public class RtspServerPipelineFactory {

	private final RtspServerStackImpl rtspServerStackImpl;

	protected RtspServerPipelineFactory(RtspServerStackImpl rtspServerStackImpl) {
		this.rtspServerStackImpl = rtspServerStackImpl;
	}

	public ChannelInitializer<SocketChannel> getPipeline() throws Exception {
		return new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast("decoder", new RtspRequestDecoder());
				pipeline.addLast("encoder", new RtspResponseEncoder());
				pipeline.addLast("handler", new RtspRequestHandler(rtspServerStackImpl));
			}
		};
	}
}
