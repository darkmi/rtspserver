package com.darkmi.nio;

import java.nio.ByteBuffer;

public class ByteBufferTest2 {

	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(10);
		for (int i = 1; i < 9; i++) {
			bb.put((byte) i);
		}

		System.out.println("pos:" + bb.position());
		System.out.println("limit:" + bb.limit());
		System.out.println("cap:" + bb.capacity());

		bb.flip();
		System.out.println("/nafter flip");
		System.out.println("pos:" + bb.position());
		System.out.println("limit:" + bb.limit());
		System.out.println("cap:" + bb.capacity());

		bb.mark();
		System.out.println("/nafter mark");
		System.out.println("pos:" + bb.position());
		System.out.println("limit:" + bb.limit());
		System.out.println("cap:" + bb.capacity());

		bb.reset();
		System.out.println("/nafter reset");
		System.out.println("pos:" + bb.position());
		System.out.println("limit:" + bb.limit());
		System.out.println("cap:" + bb.capacity());

		bb.clear();
		System.out.println("/nafter clear");
		System.out.println("pos:" + bb.position());
		System.out.println("limit:" + bb.limit());
		System.out.println("cap:" + bb.capacity());

		bb.limit(1);
		bb.put((byte) 9);
		bb.put((byte) 10);//超出limit范围，抛出java.nio.BufferOverflowException异常
		bb.put((byte) 11);
	}
}
