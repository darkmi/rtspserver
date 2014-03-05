package com.darkmi.server.core;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

/**
 * 
 * @author darkmi
 *
 */
public class RtspClientStackImpl implements RtspStack {
	private static Logger logger = Logger.getLogger(RtspClientStackImpl.class);
	private final String address;
	private final int port;
	private final InetAddress inetAddress;
	private Channel channel = null;
	//private ClientBootstrap bootstrap;
	private RtspListener listener = null;

	public RtspClientStackImpl(String address, int port) throws UnknownHostException {
		this.address = address;
		this.port = port;
		this.inetAddress = InetAddress.getByName(this.address);

	}

	public String getAddress() {
		return this.address;
	}

	public int getPort() {
		return this.port;
	}

	public void start() {

//		InetSocketAddress bindAddress = new InetSocketAddress(this.inetAddress, this.port);
//
//		// Configure the client.
//		bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(),
//				Executors.newCachedThreadPool()));
//
//		bootstrap.setOption("localAddress", bindAddress);
//
//		// Set up the event pipeline factory.
//		bootstrap.setPipelineFactory(new RtspClientPipelineFactory(this));
//
//		logger.info("Mobicents RTSP Client started and bound to " + bindAddress.toString());

	}

	protected void processRtspResponse(HttpResponse rtspResponse) {
		synchronized (this.listener) {
			listener.onRtspResponse(rtspResponse);
		}
	}

	protected void processRtspRequest(HttpRequest rtspRequest, Channel channel) {
		synchronized (this.listener) {
			listener.onRtspRequest(rtspRequest, channel);
		}
	}

	public void stop() {
//		ChannelFuture cf = channel.getCloseFuture();
//		cf.addListener(new ClientChannelFutureListener());
//
//		channel.close();
//		cf.awaitUninterruptibly();
//		bootstrap.getFactory().releaseExternalResources();

	}

	public void setRtspListener(RtspListener listener) {
		this.listener = listener;

	}

	private class ClientChannelFutureListener implements ChannelFutureListener {

		public void operationComplete(ChannelFuture arg0) throws Exception {
			logger.info("Mobicents RTSP Client Stop complete");
		}

	}

	public void sendRequest(HttpRequest rtspRequest, String host, int port) {

//		ChannelFuture future = null;
//		if (channel == null || (channel != null && !channel.isConnected())) {
//			// Start the connection attempt.
//			future = bootstrap.connect(new InetSocketAddress(host, port));
//		}
//
//		// Wait until the connection attempt succeeds or fails.
//		channel = future.awaitUninterruptibly().getChannel();
//		if (!future.isSuccess()) {
//			future.getCause().printStackTrace();
//			// bootstrap.releaseExternalResources();
//			return;
//		}
//
//		channel.write(rtspRequest);
	}

}
