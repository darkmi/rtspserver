package com.darkmi.rtsp;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.util.HashedWheelTimer;

/**
 * SM客户端实现.
 * @author Administrator
 *
 */
public class SMClientStackImpl implements RtspStack, Runnable {
	private static Logger logger = Logger.getLogger(SMClientStackImpl.class);
	private final String address;
	private final int port;
	private final InetAddress inetAddress;
	private Channel channel = null;
	private ClientBootstrap bootstrap;
	private RtspListener listener = null;

	/**
	 * 构造函数.
	 * @param address
	 * @param port
	 * @throws UnknownHostException
	 */
	public SMClientStackImpl(String address, int port) throws UnknownHostException {
		this.address = address;
		this.port = port;
		this.inetAddress = InetAddress.getByName(this.address);
	}

	/**
	 * 启动客户端.
	 */
	@Override
	public void start() {
		InetSocketAddress bindAddress = new InetSocketAddress(this.inetAddress, this.port);
		// Configure the client.

		bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool()));

		//bootstrap.setOption("localAddress", bindAddress);
		// Set up the event pipeline factory.
		bootstrap.setPipelineFactory(new SMPipelineFactory(this, new HashedWheelTimer()));
		bootstrap.setOption("allIdleTime", "5"); //这里，很重要 
		bootstrap.setOption("keepAlive", true);

		logger.debug("SM Client started and bound to " + bindAddress.toString());
	}

	/**
	 * 停止客户端.
	 */
	@Override
	public void stop() {
		ChannelFuture cf = channel.getCloseFuture();
		cf.addListener(new ClientChannelFutureListener());
		channel.close();
		cf.awaitUninterruptibly();
		bootstrap.getFactory().releaseExternalResources();
	}

	/**
	 * 发送请求.
	 */
	@Override
	public void sendRquest(final HttpRequest rtspRequest) {
		logger.debug("sm address -> " + address);
		logger.debug("sm port -> " + port);
		ChannelFuture future = bootstrap.connect(new InetSocketAddress(address, port));
		future.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture cf) throws Exception {
				cf.getChannel().write(rtspRequest);
			}
		});
	}

	/**
	 * 处理请求.
	 * @param rtspRequest
	 * @param channel
	 */
	protected void processRtspRequest(HttpRequest rtspRequest, Channel channel) {
		synchronized (this.listener) {
			listener.onRtspRequest(rtspRequest, channel);
		}
	}

	/**
	 * 处理响应.
	 * @param rtspResponse
	 */
	protected void processRtspResponse(HttpResponse rtspResponse) {
		synchronized (this.listener) {
			listener.onRtspResponse(rtspResponse);
		}
	}

//	/**
//	 * 处理响应.
//	 * @param rtspResponse
//	 */
//	protected void processRtspResponse(String rtspResponse) {
//		synchronized (this.listener) {
//			listener.onRtspResponse(rtspResponse);
//		}
//	}

	//	/**
	//	 * 处理响应.
	//	 * @param rtspResponse
	//	 */
	//	protected void processRtspResponse(DefaultHttpChunk rtspResponse) {
	//		synchronized (this.listener) {
	//			listener.onRtspResponse(rtspResponse);
	//		}
	//	}

	@Override
	public void setRtspListener(RtspListener listener) {
		this.listener = listener;
	}

	public String getAddress() {
		return this.address;
	}

	public int getPort() {
		return this.port;
	}

	private class ClientChannelFutureListener implements ChannelFutureListener {
		public void operationComplete(ChannelFuture arg0) throws Exception {
			logger.info("RTSP Client Stop complete");
		}
	}

	@Override
	public void run() {
		start();
	}
}
