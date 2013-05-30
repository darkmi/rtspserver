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
public class SetupServer {
	private static Logger logger = LoggerFactory.getLogger(SetupServer.class);
	private static final int MIN_READ_BUFFER_SIZE = 4096;
	private SetupMessageHandler setupMessageHandler;
	private IoAcceptor acceptor;
	private int setupPort;

	/**
	 * 启动服务.
	 */
	public void start() {
		acceptor = new NioSocketAcceptor(Runtime.getRuntime().availableProcessors() + 1);
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MessageCodecFactory()));
		acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
		acceptor.setHandler(setupMessageHandler);
		acceptor.getSessionConfig().setMinReadBufferSize(MIN_READ_BUFFER_SIZE);
		try {
			logger.debug("vvs setup start, port={}", setupPort);
			acceptor.bind(new InetSocketAddress(setupPort));
		} catch (IOException e) {
			logger.warn("绑定端口错误", e);
		}
	}

	/**
	 * 停止服务
	 */
	public void stop() {
		acceptor.unbind();
	}

	/*-----------   Setters   --------------*/

	@Autowired
	public void setSetupMessageHandler(SetupMessageHandler setupMessageHandler) {
		this.setupMessageHandler = setupMessageHandler;
	}

	@Autowired
	public void setSetupPort(int setupPort) {
		this.setupPort = setupPort;
	}
}