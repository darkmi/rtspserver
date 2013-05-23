package com.darkmi.mina.udp;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UDPServer {
	
	private final static Logger log = LoggerFactory.getLogger(UDPServer.class);
	
	public static void main(String[] args) throws Exception {
		//创建IoService
		IoAcceptor acceptor = new NioDatagramAcceptor();
		//添加编码过滤器
		acceptor.getFilterChain().addLast("protocol", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		//添加日志过滤器
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		//设置消息过滤器
		acceptor.setHandler(new UDPServerHandler());
		
		//((DatagramSessionConfig) acceptor.getSessionConfig()).setReuseAddress(true);
		
		acceptor.bind(new InetSocketAddress(554));
		log.debug("UDPServer启动.");
	}
}
