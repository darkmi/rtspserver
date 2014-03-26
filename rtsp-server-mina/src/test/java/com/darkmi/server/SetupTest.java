package com.darkmi.server;

import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.darkmi.server.rtsp.MessageCodecFactory;

public class SetupTest extends IoHandlerAdapter {
	private IoConnector connector;
	private String request;
	private String response;

	@Before
	public void start() {
		//prepare client
		IoConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(30000);
		ProtocolCodecFilter myFilter = new ProtocolCodecFilter(new MessageCodecFactory());
		connector.getFilterChain().addLast("codec", myFilter);
		connector.setHandler(this);
		
		//prepare request
		
	}

	@Test
	public void setupTest() {
		connector.connect(new InetSocketAddress("192.168.14.116", 554));

	}

	@After
	public void stop() {

	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		session.write(request);
		
	}
	
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		super.messageReceived(session, message);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		super.exceptionCaught(session, cause);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		super.messageSent(session, message);
	}
}