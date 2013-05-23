package com.darkmi.ivr.entity.send;

import java.nio.ByteBuffer;

import myUtil.ConversionUtil;

public class RequestHeader {

	public RequestHeader() {
	}

	
	public static final int LENGTH = 4;//报文头字节定长

	private int protocol;//协议编号
	private int version;//软件版本
	private short bodyLength;//包体长度Data_Len-2
	
	public short getBodyLength() {
		return bodyLength;
	}

	public void setBodyLength(Integer bodyLength) {
		String str = Integer.toString(bodyLength);
		this.bodyLength = Short.valueOf(str);
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getProtocol() {
		return protocol;
	}

	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}
	
	
	/**
	 * 以ByteBuffer返回值
	 * @return
	 */
	public byte[] toBytes(){
		ByteBuffer buffer =ByteBuffer.allocate(50);
		buffer.put(ConversionUtil.int2bytes(protocol,1));
		buffer.put(ConversionUtil.int2bytes(version,1));
		buffer.put(ConversionUtil.short2LH(bodyLength));
		buffer.flip();
		byte[] b = new byte[buffer.limit()];
		buffer.get(b);
		return b;
	}

}
