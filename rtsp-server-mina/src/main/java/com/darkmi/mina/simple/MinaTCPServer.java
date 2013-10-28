package com.darkmi.mina.simple;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaTCPServer {
	
	private static IoAcceptor acceptor;

	public void start() {
		acceptor = new NioSocketAcceptor(Runtime.getRuntime().availableProcessors() + 1);
		acceptor.getFilterChain().addLast("protocol", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		acceptor.setHandler(new TCPMessageHandler());
		acceptor.getSessionConfig().setMinReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 35);
		try {
			acceptor.bind(new InetSocketAddress("172.0.0.1", 8060));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		acceptor.unbind();
	}

	public static void main(String[] args) {
		MinaTCPServer server = new MinaTCPServer();
		server.start();
	}
}
