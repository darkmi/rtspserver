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

import com.darkmi.server.rtsp.MessageCodecFactory;

public class RtspServer {
	private static Logger logger = LoggerFactory.getLogger(RtspServer.class);
	private static final int MIN_READ_BUFFER_SIZE = 4096;
	private RtspMessageHandler messageHandler;
	private IoAcceptor acceptor;
	private String ip;
	private int port;

	public void start() {
		acceptor = new NioSocketAcceptor(Runtime.getRuntime().availableProcessors() + 1);
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MessageCodecFactory()));
		acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
		acceptor.setHandler(messageHandler);
		acceptor.getSessionConfig().setMinReadBufferSize(MIN_READ_BUFFER_SIZE);
		try {
			acceptor.bind(new InetSocketAddress(ip, port));
			logger.debug("RTSP Server start, ip={" + ip + "}, port={}", port);
		} catch (IOException e) {
			logger.warn("bind port error...", e);
		}
	}

	public void stop() {
		acceptor.unbind();
	}

	/*-----------   Setters   --------------*/

	public void setMessageHandler(RtspMessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}