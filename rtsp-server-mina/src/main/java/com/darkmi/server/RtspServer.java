package com.darkmi.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.darkmi.server.rtsp.MessageCodecFactory;

/**
 * 启动555端口.
 */
public class RtspServer {
	private static Logger logger = LoggerFactory.getLogger(RtspServer.class);
	private static final int MIN_READ_BUFFER_SIZE = 4096;
	private RtspMessageHandler messageHandler;
	private IoAcceptor acceptor;
	private int setupPort;
	private int playPort;

	public void start() {
		acceptor = new NioSocketAcceptor(Runtime.getRuntime().availableProcessors() + 1);
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MessageCodecFactory()));
		acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
		acceptor.setHandler(messageHandler);
		acceptor.getSessionConfig().setMinReadBufferSize(MIN_READ_BUFFER_SIZE);
		try {
			acceptor.bind(new InetSocketAddress(setupPort));
			acceptor.bind(new InetSocketAddress(playPort));
			logger.debug("RTSP Server start, setupport={}, playport={}", setupPort, playPort);
		} catch (IOException e) {
			logger.warn("bind port error...", e);
		}
	}

	public void stop() {
		acceptor.unbind();
	}

	/*-----------   Setters   --------------*/

	@Autowired
	public void setSetupMessageHandler(RtspMessageHandler setupMessageHandler) {
		this.messageHandler = setupMessageHandler;
	}

	@Autowired
	public void setSetupPort(int setupPort) {
		this.setupPort = setupPort;
	}
}