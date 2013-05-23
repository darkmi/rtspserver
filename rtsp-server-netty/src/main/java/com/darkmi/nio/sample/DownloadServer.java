package com.darkmi.nio.sample;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;

//模拟下载服务
public class DownloadServer<T> implements Callable<T>{
	private Selector selector;//创建全局selector
	private Map<SocketChannel, Handle> map = new HashMap<SocketChannel, Handle>();//socketChannel和handle之间的映射

	//创建一个服务器serverSocketChannel,并与selector进行注册
	public DownloadServer() throws Exception {
		selector = Selector.open();
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.socket().bind(new InetSocketAddress(1234));
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
	}

	//对selector.select进行迭代,并依次进行处理
	public T call() throws Exception {
		System.out.println("startTo listen in 1234....");
		for(; ;) {
			selector.select();
			Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
			while(keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();
				if(key.isValid())
					handle(key);
				keyIterator.remove();
			}
		}
	}

	//处理每个key,对于acceptable的key,由主类进行处理,而其他事件,则由内部类进行处理
	private void handle(final SelectionKey key) throws Exception {
		if(key.isAcceptable()) {
			ServerSocketChannel channel = (ServerSocketChannel) key.channel();
			SocketChannel socketChannel = channel.accept();
			socketChannel.configureBlocking(false);
			socketChannel.register(selector, SelectionKey.OP_READ);//注册读事件
			map.put(socketChannel, new Handle());//把socket和handle进行绑定
		}
		//用map中的handle处理read和write事件,以模拟多个文件同时进行下载
		if(key.isReadable() || key.isWritable()) {
			SocketChannel socketChannel = (SocketChannel) key.channel();
			final Handle handle = map.get(socketChannel);
			if(handle != null)
				handle.handle(key);
		}
	}

	//内部类,模拟一个内部类处理一个文件下载服务,多个类可以处理多个文件下载服务
	private class Handle{
		private StringBuilder message;
		private boolean writeOK = true;
		private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		private FileChannel fileChannel;
		private String fileName;

		private void handle(SelectionKey key) throws Exception {
			if(key.isReadable()) {
				SocketChannel socketChannel = (SocketChannel) key.channel();
				if(writeOK)
					message = new StringBuilder();
				while(true) {
					byteBuffer.clear();
					int r = socketChannel.read(byteBuffer);
					if(r == 0)
						break;
					if(r == -1) {
						socketChannel.close();
						key.cancel();
						return;
					}
					message.append(new String(byteBuffer.array(), 0, r));
				}
				//将接收到的信息转化成文件名,以映射到服务器上的指定文件
				if(writeOK && invokeMessage(message)) {
					socketChannel.register(selector, SelectionKey.OP_WRITE);
					writeOK = false;
				}
			}
			//向客户端写数据
			if(key.isWritable()) {
				if(!key.isValid())
					return;
				SocketChannel socketChannel = (SocketChannel) key.channel();
				if(fileChannel == null)
					fileChannel = new FileInputStream(fileName).getChannel();
				byteBuffer.clear();
				int w = fileChannel.read(byteBuffer);
				//如果文件已写完,则关掉key和socket
				if(w <= 0) {
					fileName = null;
					fileChannel.close();
					fileChannel = null;
					writeOK = true;
					socketChannel.close();
					key.channel();
					return;
				}
				byteBuffer.flip();
				socketChannel.write(byteBuffer);
			}
		}

		//将信息转化成文件名
		private boolean invokeMessage(StringBuilder message) {
			String m = message.toString();
			try {
				File f = new File(m);
				if(!f.exists())
					return false;
				fileName = m;
				return true;
			} catch(Exception e) {
				return false;
			}
		}

	}

	public static void main(String[] args) throws Exception {
		/*
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(new DownloadServer<Object>());
		executorService.shutdown();
		*/
		new DownloadServer().call();
	}
}
