package com.darkmi.nio.one;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ChannelAcceptNew {
	public static final String GREETING = "Hello I must be going.\r\n";

	public static void main(String[] argv) throws Exception {
		int port = 1234; //默认端口号
		//创建选择器
		Selector selector = Selector.open();
		
		//ByteBuffer buffer = ByteBuffer.wrap(GREETING.getBytes());
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(port));
		ssc.configureBlocking(false);
		ssc.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {
			if (selector.select(3000) == 0) {
				System.out.println(".");
				continue;
			}

			Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
			while (keyIter.hasNext()) {
				SelectionKey key = (SelectionKey) keyIter.next();

				if (key.isAcceptable()) {
					System.out.println("acceptable");
					SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
					sc.configureBlocking(false);
					sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(256));
				}

				if (key.isReadable()) {
					System.out.println("readable");
				}

				if (key.isWritable()) {
					System.out.println("writeable");
				}

				if (key.isConnectable()) {
					System.out.println("connectable");
				}
			}
		}
	}
}
