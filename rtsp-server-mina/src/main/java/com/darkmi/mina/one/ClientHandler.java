package com.darkmi.mina.one;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class ClientHandler extends IoHandlerAdapter {

	private final String values;

	public ClientHandler(String values) {
		this.values = values;
	}

	@Override
	public void sessionOpened(IoSession session) {
		session.write(values);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		System.out.println("Server send --> " + message);
	}

}
