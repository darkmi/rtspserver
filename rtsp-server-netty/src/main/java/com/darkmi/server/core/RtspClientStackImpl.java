package com.darkmi.server.core;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.netty.handler.codec.rtsp.RtspMethods;
import io.netty.handler.codec.rtsp.RtspVersions;

import java.net.UnknownHostException;

import org.apache.log4j.Logger;

/**
 * 
 * @author darkmi
 *
 */
public class RtspClientStackImpl implements RtspStack {
	private static Logger logger = Logger.getLogger(RtspClientStackImpl.class);
	private String address;
	private int port;
	//private final InetAddress inetAddress;
	//private Channel channel = null;
	private Bootstrap rtspClient;
	private RtspListener listener = null;

	public RtspClientStackImpl() {
	}

	public RtspClientStackImpl(String address, int port) throws UnknownHostException {
		this.address = address;
		this.port = port;
	}

	public void start() {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			rtspClient = new Bootstrap();
			rtspClient.group(group).channel(NioSocketChannel.class)
					.handler(new RtspClientPipelineFactory(this).getPipeline());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Shut down executor threads to exit.
			group.shutdownGracefully();
		}
	}

	public void stop() {
		//ChannelFuture cf = channel.getCloseFuture();
		//cf.addListener(new ClientChannelFutureListener());
		//channel.close();
		//cf.awaitUninterruptibly();
		//bootstrap.getFactory().releaseExternalResources();

	}

	protected void processRtspResponse(HttpResponse rtspResponse) {
		synchronized (this.listener) {
			listener.onRtspResponse(rtspResponse);
		}
	}

	protected void processRtspRequest(HttpRequest rtspRequest, ChannelHandlerContext ctx) {
		synchronized (this.listener) {
			listener.onRtspRequest(rtspRequest, ctx);
		}
	}

	public void sendRequest(HttpRequest rtspRequest, String host, int port) {
		Channel channel = connect(host, port);
		// Send the HTTP request.
		logger.debug("准备发送请求...");
		channel.writeAndFlush(rtspRequest);
		logger.debug("请求发送完毕...");
		// Wait for the server to close the connection.
		try {
			channel.closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Channel connect(String ip, int port) {
		// Make the connection attempt.
		Channel ch = null;
		try {
			logger.debug("准备连接服务器...");
			ch = rtspClient.connect(ip, port).sync().channel();
			logger.debug("服务器连接成功...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return ch;
	}

	public String getAddress() {
		return this.address;
	}

	public int getPort() {
		return this.port;
	}

	public void setRtspListener(RtspListener listener) {
		this.listener = listener;
	}

	public static void main(String[] args) throws UnknownHostException {
		// Prepare the RTSP request.
		StringBuffer url = new StringBuffer();
		url.append("rtsp://").append("192.168.80.50").append(":").append(554);

		StringBuffer sdp = new StringBuffer();
		sdp.append("v=0");
		sdp.append("o=- 22b03f0e-170b-4845-8c4a-1d7bac576bbc  IN IP4 0.0.0.0");
		sdp.append("s=");
		sdp.append("c=IN IP4 0.0.0.0t=0 0");
		sdp.append("a=X-playlist-item: 10002 movi2010000004329519 0-");
		sdp.append("m=video 0 udp MP2T");

		FullHttpRequest req = new DefaultFullHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.SETUP, url.toString(),
				Unpooled.wrappedBuffer(sdp.toString().getBytes()));
		req.headers().set(RtspHeaders.Names.CSEQ, "10");

		req.headers().set(RtspHeaders.Names.CONTENT_TYPE, "application/sdp");
		req.headers().set("OnDemandSessionId", "22b03f0e-170b-4845-8c4a-1d7bac576bbc");
		req.headers().set(RtspHeaders.Names.REQUIRE, "com.comcast.ngod.r2");
		req.headers().set("SessionGroup", "80333.20");
		req.headers().set("StreamControlProto", "rtsp");

		req.headers()
				.set(RtspHeaders.Names.TRANSPORT,
						"MP2T/DVBC/UDP;unicast;destination=192.168.88.1;client_port=782;bandwidth=10005600;client=;sop_group=CDN-OSTR1-F;sop_name=CDN-OSTR1-F");

		req.headers().set(RtspHeaders.Names.USER_AGENT, "NSS/1.16");
		req.headers().set("Volume", "/files");
		req.headers().set("Content-Length", sdp.length());
		req.headers().set("Date", "Mon, 24 Feb 2014 03:21:44 GMT");

		logger.debug("video server setup =====> \n" + req);

		RtspClientStackImpl client = new RtspClientStackImpl();
		client.start();
		client.sendRequest(req, "192.168.80.50", 554);

	}
}
