package com.darkmi.mina.udpone;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryMonitor {
	
	private final static Logger logger = LoggerFactory.getLogger(MemoryMonitor.class);
	public static final int PORT = 554;

	public MemoryMonitor() throws IOException {
		// 创建UDP数据包NIO
		NioDatagramAcceptor acceptor = new NioDatagramAcceptor();

		// 设置filter
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		chain.addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		chain.addLast("logger", new LoggingFilter());

		// NIO设置底层IOHandler 把服务器的本身传入
		acceptor.setHandler(new MemoryMonitorHandler());

		// 设置是否重用地址？ 也就是每个发过来的udp信息都是一个地址？
		DatagramSessionConfig dcfg = acceptor.getSessionConfig();
		dcfg.setReuseAddress(true);

		// 绑定端口地址
		acceptor.bind(new InetSocketAddress(PORT));
		logger.debug("UDPServer listening on port " + PORT);
	}

	public static void main(String[] args) {
		try {
			new MemoryMonitor();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
