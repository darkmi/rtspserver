package com.darkmi.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferDemo {
	public static void main(String[] args) throws Exception {
		//分配一个非直接缓冲区
		ByteBuffer bb = ByteBuffer.allocate(100);
		//向缓冲区写入0到100的字节制
		for (int i = 0; i < 100; i++) {
			byte b = (byte) (Math.random() * 100);
			bb.put(b);
		}

		System.out.println("写入文件前的缓冲区数据");
		bb.flip();
		while (bb.hasRemaining())
			System.out.print(bb.get() + " ");
		System.out.println();

		//获取一个关联到文件buffer.txt的信道
		FileChannel fc = new FileOutputStream("buffer.txt").getChannel();
		//将缓冲区数据写到文件中
		bb.flip();
		fc.write(bb);
		//防止缓存
		fc.force(true);
		//关闭信道
		fc.close();
		bb = null;
		fc = null;

		//下面从文件中读取数据
		fc = new FileInputStream("buffer.txt").getChannel();
		ByteBuffer bb2 = ByteBuffer.allocate((int) fc.size());
		fc.read(bb2);
		System.out.println("从文件读取的缓冲区数据");
		bb2.flip();
		while (bb2.hasRemaining())
			System.out.print(bb2.get() + " ");
		System.out.println();
		fc.close();
		bb2 = null;
		fc = null;
	}
}
