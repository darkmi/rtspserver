package com.darkmi.vvs;

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

import cn.com.supertv.srmserver.rtsp.MessageCodecFactory;

/**
 * 启动8070端口.
 */
public class PlayServer {
	private static Logger logger = LoggerFactory.getLogger(PlayServer.class);
	private static final int MIN_READ_BUFFER_SIZE = 4096;
	private IoAcceptor acceptor;
	private PlayMessageHandler playMessageHandler;
	private int playPort;
	
	/**
	 * 启动服务.
	 */
	public void start() {
		acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MessageCodecFactory()));
		acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
		//acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newFixedThreadPool(2000)));
		acceptor.setHandler(playMessageHandler);
		acceptor.getSessionConfig().setMinReadBufferSize(MIN_READ_BUFFER_SIZE);
		try {
			logger.debug("vvs play start, port={}", playPort);
			acceptor.bind(new InetSocketAddress( playPort));
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
	public void setPlayMessageHandler(PlayMessageHandler playMessageHandler) {
		this.playMessageHandler = playMessageHandler;
	}
	
	@Autowired
	public void setPlayPort(int playPort) {
		this.playPort = playPort;
	}
}
