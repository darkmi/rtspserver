package com.darkmi.mina.one;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class TCPServer {
	public static void main(String[] args) {
		IoAcceptor acceptor = new NioSocketAcceptor();
		//配置编解码过滤器
		ProtocolCodecFilter myFilter = new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8")));
		acceptor.getFilterChain().addLast("codecFilter", myFilter);
		
		//配置日志过滤器
		LoggingFilter lf = new LoggingFilter();
		acceptor.getFilterChain().addLast("logging", lf);
		
		acceptor.setHandler(new TCPMessageHandler());
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		acceptor.getSessionConfig().setMinReadBufferSize(1024);
		try {
			acceptor.bind(new InetSocketAddress("127.0.0.1", 554));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
