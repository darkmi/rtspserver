package com.darkmi.phoneclient.entity.send;

import java.nio.ByteBuffer;

import myUtil.ConversionUtil;

public class RequestHeader {

	public RequestHeader() {
	}

	public static final int LENGTH = 6;// 报文头字节定长

	private int preByte = 254;// 用于在应用层保证数据完成性
	private int protocol = 2;// 协议编号
	private int version = 1;// 版本号，默认1
	private String msgID = "0500";
	private int bodyLength;// 包体长度

	public int getPreByte() {
		return preByte;
	}

	public void setPreByte(int preByte) {
		this.preByte = preByte;
	}

	public int getProtocol() {
		return protocol;
	}

	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getMsgID() {
		return msgID;
	}

	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

	public int getBodyLength() {
		return bodyLength;
	}

	public void setBodyLength(int bodyLength) {
		this.bodyLength = bodyLength;
	}

	/**
	 * 以ByteBuffer返回值
	 * 
	 * @return
	 */
	public byte[] toBytes() {
		ByteBuffer buffer = ByteBuffer.allocate(50);
		buffer.put(ConversionUtil.int2bytes(preByte, 1));
		buffer.put(ConversionUtil.int2bytes(protocol, 1));
		buffer.put(ConversionUtil.int2bytes(version, 1));
		buffer.put(ConversionUtil.str2byte(msgID, 16));
		buffer.put(ConversionUtil.int2bytes(bodyLength, 1));
		buffer.flip();
		byte[] b = new byte[buffer.limit()];
		buffer.get(b);
		return b;
	}

}
