package com.darkmi.nio;

import java.nio.ByteBuffer;

public class ByteBufferTest {

	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(10);
		
		System.out.println("pos:" + bb.position());
		System.out.println("limit:" + bb.limit());
		System.out.println("cap:" + bb.capacity());
		System.out.println("===================");
		
		bb.put((byte)0);
		bb.put((byte)1);
		bb.put((byte)2);
		bb.mark();
		bb.put((byte)3);
		bb.put((byte)4);

		
		System.out.println("pos:" + bb.position());
		System.out.println("limit:" + bb.limit());
		System.out.println("cap:" + bb.capacity());
		
		System.out.println("===================");
		bb.flip();
		System.out.println("pos:" + bb.position());
		System.out.println("limit:" + bb.limit());
		System.out.println("cap:" + bb.capacity());
		
		System.out.println("===================");
		bb.rewind();
		
		System.out.println("pos:" + bb.position());
		System.out.println("limit:" + bb.limit());
		System.out.println("cap:" + bb.capacity());
		
		System.out.println("===================");
		
		
		
//		
//		while(bb.hasRemaining()){
//			byte current = bb.get();
//			int position = bb.position();
//			System.out.println("current --> " + current);
//			System.out.println("position --> " + position);
//		}
//		
//		System.out.println("pos:" + bb.position());
//		System.out.println("limit:" + bb.limit());
//		System.out.println("cap:" + bb.capacity());
//		
//		System.out.println("===================");
		
//		bb.mark();
//		System.out.println("/nafter mark");
//		System.out.println("pos:" + bb.position());
//		System.out.println("limit:" + bb.limit());
//		System.out.println("cap:" + bb.capacity());
		
//
//		bb.flip();
//		System.out.println("/nafter flip");
//		System.out.println("pos:" + bb.position());
//		System.out.println("limit:" + bb.limit());
//		System.out.println("cap:" + bb.capacity());
//

//
//		bb.reset();
//		System.out.println("/nafter reset");
//		System.out.println("pos:" + bb.position());
//		System.out.println("limit:" + bb.limit());
//		System.out.println("cap:" + bb.capacity());
//
//		bb.clear();
//		System.out.println("/nafter clear");
//		System.out.println("pos:" + bb.position());
//		System.out.println("limit:" + bb.limit());
//		System.out.println("cap:" + bb.capacity());
//
//		bb.limit(1);
//		bb.put((byte) 9);
//		bb.put((byte) 10);
//		bb.put((byte) 11);
	}
}
