package com.darkmi.mina.command;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.darkmi.mina.simple.MinaTCPServer;

/**
 * CommandServer.
 */
public class CommandServer {
	//
	private static Logger logger = LoggerFactory.getLogger(MinaTCPServer.class);
	//
	private static IoAcceptor acceptor;

	/**
	 * 
	 */
	public void start() {
		acceptor = new NioSocketAcceptor(Runtime.getRuntime().availableProcessors() + 1);

		acceptor.getFilterChain().addLast("protocol", new ProtocolCodecFilter(new CommandCodecFactory()));
		acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));
		acceptor.setHandler(new CommandMessageHandler());
		acceptor.getSessionConfig().setMinReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 999999999);
		try {
			acceptor.bind(new InetSocketAddress("192.168.7.134", 9123));
		} catch (IOException e) {
			logger.warn("�󶨶˿ڴ���", e);
		}
	}

	/**
	 *
	 */
	public void stop() {
		acceptor.unbind();
	}

	public static void main(String[] args) {
		CommandServer server = new CommandServer();
		server.start();
	}
}