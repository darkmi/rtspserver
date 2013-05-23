package com.darkmi.phoneclient.entity.receive;

import com.darkmi.phoneclient.tool.MessageType;

/**
 * 协议头
 * @author Administrator
 *
 */
public class ResponseHeader {

	public static final int LENGTH = 6;//报文头字节定长

	private int preByte = 254;//用于在应用层保证数据完成性
	private int protocol;//协议编号
	private int version;//版本号，默认1
	private MessageType msgID;//包体长度
	private int bodyLength;//包体长度
	
	
	
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

	public MessageType getMsgID() {
		return msgID;
	}
	public void setMsgID(MessageType msgID) {
		this.msgID = msgID;
	}
	public int getBodyLength() {
		return bodyLength;
	}
	public void setBodyLength(int bodyLength) {
		this.bodyLength = bodyLength;
	}

	
}
