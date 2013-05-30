package com.darkmi.server;

import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.darkmi.server.rtsp.MessageCodecFactory;

public class TeardownRequestAction {
	
	private static Logger logger = LoggerFactory.getLogger(TeardownRequestAction.class);

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		//TEARDOWN
		sb.append("TEARDOWN rtsp://192.168.14.14/ RTSP/1.0\r\n");
		sb.append("CSeq: 5\r\n");
		sb.append("Session: 1330505201\r\n");
		sb.append("Connection: close\r\n");
		sb.append("\r\n");

		logger.debug(sb.toString());

		IoConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(30000);
		ProtocolCodecFilter myFilter = new ProtocolCodecFilter(new MessageCodecFactory());
		connector.getFilterChain().addLast("codec", myFilter);

		connector.setHandler(new ClientHandler(sb.toString()));

		connector.connect(new InetSocketAddress("192.168.14.14", 554));
	}
}
