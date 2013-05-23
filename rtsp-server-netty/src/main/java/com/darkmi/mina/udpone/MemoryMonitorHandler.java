package com.darkmi.mina.udpone;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryMonitorHandler extends IoHandlerAdapter {
	
	private final static Logger logger = LoggerFactory.getLogger(MemoryMonitorHandler.class);

	/**
	 * 服务器端收到一个消息
	 */
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		String value = message == null ? "" : message.toString();
		logger.debug("服务端接收到信息：" + value);
		session.write(value.toUpperCase());
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		cause.printStackTrace();
		session.close(true);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("服务器端关闭session...");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("服务器端成功创建一个session...");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("服务器端成功开启一个session...");
	}
}
