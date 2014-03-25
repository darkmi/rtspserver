package com.darkmi.server;

import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.junit.Before;
import org.junit.Test;

import com.darkmi.server.rtsp.MessageCodecFactory;

public class SetupTest extends IoHandlerAdapter{
	IoConnector connector;
	
	@Before
	public void connect(){
		IoConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(30000);
		ProtocolCodecFilter myFilter = new ProtocolCodecFilter(new MessageCodecFactory());
		connector.getFilterChain().addLast("codec", myFilter);
		connector.setHandler(this);
		connector.connect(new InetSocketAddress("192.168.14.116", 554));
	}
	
	@Test
	public void setupTest(){
		
	}

}
