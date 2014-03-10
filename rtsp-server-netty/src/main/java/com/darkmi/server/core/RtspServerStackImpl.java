package com.darkmi.server.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

/**
 * 
 * @author darkmi
 *
 */
public class RtspServerStackImpl implements RtspStack {
	private static Logger logger = Logger.getLogger(RtspServerStackImpl.class);
	private final String address;
	private final InetAddress inetAddress;
	private final int port;
	private RtspListener listener = null;

	private static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2;
	private static final int BIZTHREADSIZE = 4;
	private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
	private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);

	//public RtspServerStackImpl(){
	//}
	
	public RtspServerStackImpl(String address, int port) throws UnknownHostException {
		this.address = address;
		this.port = port;
		this.inetAddress = InetAddress.getByName(this.address);
	}

	@Override
	public void start() {
		try {
			InetSocketAddress bindAddress = new InetSocketAddress(this.inetAddress, this.port);
			ServerBootstrap server = new ServerBootstrap();
			server.group(bossGroup, workerGroup);
			server.channel(NioServerSocketChannel.class);
			server.childHandler(new RtspServerInitializer(this).get());
			server.bind(bindAddress).sync();
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		logger.info("ODRM Start.");
	}

	@Override
	public void stop() {
		//ChannelGroupFuture future = allChannels.close();
		//future.awaitUninterruptibly();
		//bootstrap.getFactory().releaseExternalResources();
	}

	@Override
	public void sendRequest(HttpRequest rtspRequest, String host, int port) {
		throw new UnsupportedOperationException("Not Supported yet");
	}

	protected void processRtspRequest(HttpRequest rtspRequest,ChannelHandlerContext ctx) {
		synchronized (this.listener) {
			listener.onRtspRequest(rtspRequest, ctx);
		}
	}

	protected void processRtspResponse(HttpResponse rtspResponse) {
		synchronized (this.listener) {
			listener.onRtspResponse(rtspResponse);
		}
	}

	@Override
	public void setRtspListener(RtspListener listener) {
		this.listener = listener;
	}

	@Override
	public String getAddress() {
		return this.address;
	}

	@Override
	public int getPort() {
		return this.port;
	}
	
	public static void main(String[] args) {
		try {
			RtspStack server = new RtspServerStackImpl("192.168.14.116", 554);
			server.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 
	}

}



