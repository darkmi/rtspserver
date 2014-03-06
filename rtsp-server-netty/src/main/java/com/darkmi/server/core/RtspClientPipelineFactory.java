package com.darkmi.server.core;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.rtsp.RtspRequestDecoder;
import io.netty.handler.codec.rtsp.RtspRequestEncoder;
import io.netty.handler.codec.rtsp.RtspResponseDecoder;
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
				pipeline.addLast("encoder", new RtspRequestEncoder());
				pipeline.addLast("decoder", new RtspResponseDecoder());
				pipeline.addLast("handler", new RtspResponseHandler(rtspClientStackImpl));
			}
		};
	}

}
