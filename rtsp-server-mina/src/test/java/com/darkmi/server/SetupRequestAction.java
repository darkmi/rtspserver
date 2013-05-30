package com.darkmi.server;

import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.darkmi.server.rtsp.MessageCodecFactory;

public class SetupRequestAction {
	private static Logger logger = LoggerFactory.getLogger(SetupRequestAction.class);

	public static void send() {
		StringBuffer sb = new StringBuffer();
		//发往虚拟视频服务器的的SETUP请求
		sb.append("SETUP rtsp://192.168.14.193:554/movie---98---2012 RTSP/1.0\r\n");
		sb.append("CSeq: 1\r\n");
		sb.append("Require: HFC.Delivery.Profile.1.0\r\n");
		sb.append("Transport: MP2T/AVP;unicast;destination=10.1.1.196;port=49160\r\n");
		sb.append("\r\n");

		logger.debug(sb.toString());

		IoConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(30000);
		ProtocolCodecFilter myFilter = new ProtocolCodecFilter(new MessageCodecFactory());
		connector.getFilterChain().addLast("codec", myFilter);
		connector.setHandler(new ClientHandler(sb.toString()));
		connector.connect(new InetSocketAddress("localhost", 554));
	}
}
