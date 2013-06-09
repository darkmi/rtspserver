package com.darkmi.mina.udp;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;

public class UDPClient {

	public static void main(String[] args) throws Exception {
		IoConnector connector = new NioDatagramConnector();

		connector.getFilterChain().addLast("protocol",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		connector.getFilterChain().addLast("logger", new LoggingFilter());

		connector.setHandler(new UDPClientHandler());
		ConnectFuture connFuture = connector.connect(new InetSocketAddress("127.0.0.1", 554));
		connFuture.addListener(new IoFutureListener<ConnectFuture>() {
			@Override
			public void operationComplete(ConnectFuture future) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				IoSession session = future.getSession();
				System.out.println("++++++++++++++++++++++++++++");
			}

		});

		System.out.println("*************");

		// 等待是否连接成功，相当于是转异步执行为同步执行。
		//connFuture.awaitUninterruptibly();
		// 连接成功后获取会话对象。如果没有上面的等待，
		//由于connect()方法是异步的，session可能会无法获取。
		IoSession session = connFuture.getSession();
		IoBuffer buffer = IoBuffer.allocate(5);
		buffer.putString("aaa\r\n", Charset.forName("UTF-8").newEncoder());
		buffer.flip();
		WriteFuture future = session.write(buffer);
		future.awaitUninterruptibly();
		connector.dispose();
	}
}
