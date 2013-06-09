package com.darkmi.mina.udp;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UDPServerHandler extends IoHandlerAdapter {

	private final static Logger log = LoggerFactory.getLogger(UDPServerHandler.class);

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		log.debug("******************* messageReceived!");
		String value = message == null ? "" : message.toString();
		log.debug("服务端接收到信息：" + value);
		session.write(value.toUpperCase());
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		log.debug("******************* Session Closed!");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		log.debug("******************* Session Created!");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		log.debug(session + "******************* Session Idle!");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		log.debug("******************* Session Opened!");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		log.error(cause.getMessage());
		session.close(false);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		log.debug("******************* messageSent!");
	}
}
