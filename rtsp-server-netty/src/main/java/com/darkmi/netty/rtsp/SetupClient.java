package com.darkmi.netty.rtsp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
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
import io.netty.util.CharsetUtil;

import org.apache.log4j.Logger;

public class SetupClient {
	private static final Logger logger = Logger.getLogger(SetupClient.class);
	public static String HOST = "192.168.80.50";
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
				pipeline.addLast("handler", new SetupHandler());
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
			url.append("rtsp://").append("192.168.80.50").append(":").append(554);

			StringBuffer sdp = new StringBuffer();
			sdp.append("v=0\r\n");
			sdp.append("o=- 22b03f0e-170b-4845-8c4a-1d7bac576bbc  IN IP4 0.0.0.0\r\n");
			sdp.append("s=\r\n");
			sdp.append("c=IN IP4 0.0.0.0t=0 0\r\n");
			sdp.append("a=X-playlist-item: 10002 movi2010000004329519 0-\r\n");
			sdp.append("m=video 0 udp MP2T\r\n");

			FullHttpRequest req = new DefaultFullHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.SETUP, url.toString(),
					Unpooled.wrappedBuffer(sdp.toString().getBytes()));
			req.headers().set(RtspHeaders.Names.CSEQ, "10");
			req.headers().set(RtspHeaders.Names.CONTENT_TYPE, "application/sdp");
			req.headers().set("OnDemandSessionId", "22b03f0e-170b-4845-8c4a-1d7bac576bbc");
			req.headers().set(RtspHeaders.Names.REQUIRE, "com.comcast.ngod.r2");
			req.headers().set("SessionGroup", "80333.20");
			req.headers().set("StreamControlProto", "rtsp");
			String trans = "MP2T/DVBC/UDP;unicast;destination=192.168.88.1;client_port=782;bandwidth=10005600;client=;sop_group=CDN-OSTR1-F;sop_name=CDN-OSTR1-F";
			req.headers().set(RtspHeaders.Names.TRANSPORT, trans);
			req.headers().set(RtspHeaders.Names.USER_AGENT, "NSS/1.16");
			req.headers().set("Volume", "/files");
			req.headers().set("Content-Length", sdp.length());
			req.headers().set("Date", "Mon, 24 Feb 2014 03:21:44 GMT");

			//req.content().setBytes(1, Unpooled.wrappedBuffer(sdp.toString().getBytes()));
			logger.debug("setup request header =====> \n" + req);
			logger.debug("setup request content =====> \n" + req.content().toString(CharsetUtil.UTF_8));

			SetupClient.sendMsg(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
