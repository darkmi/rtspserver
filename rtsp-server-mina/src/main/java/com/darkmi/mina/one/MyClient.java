package com.darkmi.mina.one;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MyClient {
	public static void main(String[] args) {
		IoConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(30000);
		ProtocolCodecFilter myFilter = new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8")));
		connector.getFilterChain().addLast("codec", myFilter);
		connector.setHandler(new ClientHandler("mixiaohui"));
		connector.connect(new InetSocketAddress("127.0.0.1", 554));
	}
}
