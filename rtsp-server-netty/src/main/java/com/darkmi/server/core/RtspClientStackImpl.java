package com.darkmi.server.core;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
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
			logger.debug("准备链接服务器...");
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
		url.append("rtsp://").append("192.168.14.116").append(":").append(554);

		FullHttpRequest req = new DefaultFullHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.SETUP, url.toString());
		req.headers().set(RtspHeaders.Names.CSEQ, "10");
		req.headers().set(RtspHeaders.Names.TRANSPORT, "MP2T/DVBC/QAM;unicast;client=00AF123456DE;qam_name=aaa");
		logger.debug("=====> \n" + req);

		RtspClientStackImpl client = new RtspClientStackImpl();
		client.start();
		client.sendRequest(req, "192.168.14.116", 554);

	}
}
