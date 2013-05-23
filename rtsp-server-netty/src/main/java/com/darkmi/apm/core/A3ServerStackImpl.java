package com.darkmi.apm.core;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;

/**
 * 注入服务端接口实现.
 * @author MiXiaohui
 *
 */
public class A3ServerStackImpl implements A3ServerStack {
	private static Logger logger = Logger.getLogger(A3ServerStackImpl.class);
	private final String address;
	private final InetAddress inetAddress;
	private final int port;
	private Channel channel = null;
	private ServerBootstrap bootstrap = null;
	private A3Listener listener = null;
	static final ChannelGroup allChannels = new DefaultChannelGroup("APM");

	/**
	 * 构造函数
	 * @param address
	 * @param port
	 * @throws UnknownHostException
	 */
	public A3ServerStackImpl(String address, int port) throws UnknownHostException {
		this.address = address;
		this.port = port;
		inetAddress = InetAddress.getByName(this.address);
	}

	@Override
	public String getAddress() {
		return this.address;
	}

	@Override
	public int getPort() {
		return this.port;
	}

	/**
	 * 启动服务器.
	 */
	@Override
	public void start() {
		InetSocketAddress bindAddress = new InetSocketAddress(this.inetAddress, this.port);
		bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(new RtspServerBossThreadFactory()),
				Executors.newCachedThreadPool(new RtspServerWorkerThreadFactory())));

		// Set up the event pipeline factory.
		bootstrap.setPipelineFactory(new A3ServerPipelineFactory(this));

		// Bind and start to accept incoming connections.
		channel = bootstrap.bind(bindAddress);
		allChannels.add(channel);
		logger.info("APM of Video Server started and bound to " + bindAddress.toString());
	}

	/**
	 * 关闭服务器.
	 */
	@Override
	public void stop() {
		ChannelGroupFuture future = allChannels.close();
		future.awaitUninterruptibly();
		bootstrap.getFactory().releaseExternalResources();
	}

	@Override
	public void setA3Listener(A3Listener listener) {
		this.listener = listener;
	}

	@Override
	public void sendRquest(HttpRequest rtspRequest, String host, int port) {
		throw new UnsupportedOperationException("Not Supported yet");
	}

	protected void processA3Request(HttpRequest rtspRequest, Channel channel) {
		synchronized (this.listener) {
			listener.onA3Request(rtspRequest, channel);
		}
	}

	protected void processA3Response(HttpResponse rtspResponse) {
		synchronized (this.listener) {
			listener.onA3Response(rtspResponse);
		}
	}

	@SuppressWarnings("unused")
	private class ServerChannelFutureListener implements ChannelFutureListener {
		public void operationComplete(ChannelFuture arg0) throws Exception {
			logger.info("Mobicents RTSP Server Stop complete");
		}
	}
}

class RtspServerBossThreadFactory implements ThreadFactory {
	public static final AtomicLong sequence = new AtomicLong(0);
	private ThreadGroup factoryTG = new ThreadGroup("APMBossThreadGroup[" + sequence.incrementAndGet() + "]");

	public Thread newThread(Runnable r) {
		Thread t = new Thread(this.factoryTG, r);
		t.setPriority(Thread.NORM_PRIORITY);
		return t;
	}
}

class RtspServerWorkerThreadFactory implements ThreadFactory {
	public static final AtomicLong sequence = new AtomicLong(0);
	private ThreadGroup factoryTG = new ThreadGroup("APMWorkerThreadGroup[" + sequence.incrementAndGet() + "]");

	public Thread newThread(Runnable r) {
		Thread t = new Thread(this.factoryTG, r);
		t.setPriority(Thread.NORM_PRIORITY);
		return t;
	}
}