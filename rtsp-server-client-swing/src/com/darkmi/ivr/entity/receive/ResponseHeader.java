package com.darkmi.ivr.entity.receive;

/**
 * 协议头
 * @author Administrator
 *
 */
public class ResponseHeader {

	public static final int LENGTH = 4;//报文头字节定长

	private int protocol;//协议编号
	private int version;//软件版本
	private int bodyLength;//包体长度Data_Len-2
	

	public int getBodyLength() {
		return bodyLength;
	}

	public void setBodyLength(int bodyLength) {
		this.bodyLength = bodyLength;
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


}
