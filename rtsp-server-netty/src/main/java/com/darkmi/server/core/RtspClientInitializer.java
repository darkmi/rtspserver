package com.darkmi.server.core;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.rtsp.RtspRequestEncoder;
import io.netty.handler.codec.rtsp.RtspResponseDecoder;

public class RtspClientInitializer {

	private final RtspClientStackImpl rtspClientStackImpl;

	public RtspClientInitializer(RtspClientStackImpl rtspClientStackImpl) {
		this.rtspClientStackImpl = rtspClientStackImpl;
	}

	public ChannelInitializer<SocketChannel> get() {
		return new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast("encoder", new RtspRequestEncoder());
				pipeline.addLast("decoder", new RtspResponseDecoder());
				pipeline.addLast("aggregator", new HttpObjectAggregator(1048576));
				pipeline.addLast("handler", new RtspResponseHandler(rtspClientStackImpl));
			}
		};
	}

}
