package com.darkmi.nettydemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.netty.handler.codec.rtsp.RtspMethods;
import io.netty.handler.codec.rtsp.RtspRequestEncoder;
import io.netty.handler.codec.rtsp.RtspResponseDecoder;
import io.netty.handler.codec.rtsp.RtspVersions;

import org.apache.log4j.Logger;

public class RtspClient {
	
	private static final Logger logger = Logger.getLogger(TcpClient.class);
	public static String HOST = "192.168.14.116";
	public static int PORT = 554;
	public static Bootstrap bootstrap = getBootstrap();
	public static Channel channel = getChannel(HOST, PORT);

	/**	 
	 * 初始化Bootstrap	 
	 * @return	 
	 **/
	public static final Bootstrap getBootstrap() {
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group).channel(NioSocketChannel.class);
		b.handler(new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast("encoder", new RtspRequestEncoder());
				pipeline.addLast("decoder", new RtspResponseDecoder());
				pipeline.addLast("handler", new RtspClientHandler());
			}
		});
		b.option(ChannelOption.SO_KEEPALIVE, true);
		return b;
	}

	public static final Channel getChannel(String host, int port) {
		Channel channel = null;
		try {
			channel = bootstrap.connect(host, port).sync().channel();
		} catch (Exception e) {
			logger.error(String.format("连接Server(IP[%s],PORT[%s])失败", host, port), e);
			return null;
		}
		return channel;
	}

	public static void sendMsg(FullHttpRequest msg) throws Exception {
		if (channel != null) {
			channel.writeAndFlush(msg).sync();
		} else {
			logger.warn("消息发送失败,连接尚未建立!");
		}
	}

	public static void main(String[] args) throws Exception {
		try {
			
			
			// Prepare the RTSP request.
			StringBuffer url = new StringBuffer();
			url.append("rtsp://").append("192.168.14.116").append(":").append(554);

			FullHttpRequest req = new DefaultFullHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.SETUP, url.toString());
			req.headers().set(RtspHeaders.Names.CSEQ, "10");
			req.headers().set(RtspHeaders.Names.TRANSPORT, "MP2T/DVBC/QAM;unicast;client=00AF123456DE;qam_name=aaa");
			logger.debug("=====> \n" + req);
			
			RtspClient.sendMsg(req);
			//long t0 = System.nanoTime();
			//for (int i = 0; i < 100000; i++) {
			//	TcpClient.sendMsg(i + "你好1");
			//}
			//long t1 = System.nanoTime();
			//System.out.println((t1 - t0) / 1000000.0);
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}

}
