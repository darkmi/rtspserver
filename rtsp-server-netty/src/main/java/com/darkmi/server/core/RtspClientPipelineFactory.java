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
public class RtspClientPipelineFactory {

	private final RtspClientStackImpl rtspClientStackImpl;

	public RtspClientPipelineFactory(RtspClientStackImpl rtspClientStackImpl) {
		this.rtspClientStackImpl = rtspClientStackImpl;
	}

	public ChannelInitializer<SocketChannel> getPipeline() throws Exception {
		return new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast("decoder", new RtspRequestDecoder());
				pipeline.addLast("encoder", new RtspResponseEncoder());
				pipeline.addLast("handler", new RtspResponseHandler(rtspClientStackImpl));
			}
		};
	}

}
