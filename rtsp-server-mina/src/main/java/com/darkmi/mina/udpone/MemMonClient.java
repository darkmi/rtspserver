package com.darkmi.mina.udpone;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sends its memory usage to the MemoryMonitor server.
 * 这样的写法  将本来可以单独的client写到IoHandlerAdapter 更紧凑
 */
public class MemMonClient extends IoHandlerAdapter {

	private final static Logger logger = LoggerFactory.getLogger(MemMonClient.class);

	private IoSession session;
	private IoConnector connector;

	/**
	 * Default constructor.
	 */
	public MemMonClient() {
		connector = new NioDatagramConnector();
		// 设置filter
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
		chain.addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		chain.addLast("logger", new LoggingFilter());

		connector.setHandler(this);
		ConnectFuture connFuture = connector.connect(new InetSocketAddress("localhost", 554));
		connFuture.awaitUninterruptibly();
		// 给conn添加一个监听器
		connFuture.addListener(new IoFutureListener<ConnectFuture>() {
			public void operationComplete(ConnectFuture future) {
				if (future.isConnected()) {
					session = future.getSession();
					try {
						sendData();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					try {
						throw new Exception(" 连接错误。 ");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	private void sendData() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			session.write("aaa"); // 写入

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				throw new InterruptedException(e.getMessage());
			}
		}
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		logger.debug("客户端接收到信息："+message.toString());
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
	}

	public static void main(String[] args) {
		new MemMonClient();
	}
}
