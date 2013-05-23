package com.darkmi.nio.one;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;

public class HelloWorldServer {
	static int BLOCK = 1024;
	static String name = "";
	protected Selector selector;
	protected ByteBuffer clientBuffer = ByteBuffer.allocate(BLOCK);
	protected CharsetDecoder decoder;
	static CharsetEncoder encoder = Charset.forName("GB2312").newEncoder();

	public HelloWorldServer(int port) throws IOException {
		selector = this.getSelector(port);
		Charset charset = Charset.forName("GB2312");
		decoder = charset.newDecoder();
	}

	// 获取Selector
	protected Selector getSelector(int port) throws IOException {
		ServerSocketChannel server = ServerSocketChannel.open();
		Selector sel = Selector.open();
		server.socket().bind(new InetSocketAddress(port));
		server.configureBlocking(false);
		server.register(sel, SelectionKey.OP_ACCEPT);
		return sel;
	}

	// 监听端口
	public void listen() {
		try {
			for (;;) {
				selector.select();
				Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
				while (iter.hasNext()) {
					SelectionKey key = (SelectionKey) iter.next();
					iter.remove();
					process(key);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 处理事件
	protected void process(SelectionKey key) throws IOException {
		if (key.isAcceptable()) { // 接收请求
			ServerSocketChannel server = (ServerSocketChannel) key.channel();
			SocketChannel channel = server.accept();
			//设置非阻塞模式
			channel.configureBlocking(false);
			channel.register(selector, SelectionKey.OP_READ);
		} else if (key.isReadable()) { // 读信息
			SocketChannel channel = (SocketChannel) key.channel();
			int count = channel.read(clientBuffer);
			if (count > 0) {
				clientBuffer.flip();
				CharBuffer charBuffer = decoder.decode(clientBuffer);
				name = charBuffer.toString();
				System.out.println(name);
				SelectionKey sKey = channel.register(selector, SelectionKey.OP_WRITE);
				sKey.attach(name);
			} else {
				channel.close();
			}
			clientBuffer.clear();
		} else if (key.isWritable()) { // 写事件
			SocketChannel channel = (SocketChannel) key.channel();
			String name = (String) key.attachment();
			ByteBuffer block = encoder.encode(CharBuffer.wrap("Hello !" + name));
			channel.write(block);
			channel.close();
		}
	}

	public static void main(String[] args) {
		int port = 8888;
		try {
			HelloWorldServer server = new HelloWorldServer(port);
			System.out.println("listening on " + port);
			server.listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
