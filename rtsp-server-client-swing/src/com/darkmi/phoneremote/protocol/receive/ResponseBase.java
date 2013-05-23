package com.darkmi.phoneremote.protocol.receive;

import com.darkmi.phoneremote.entity.receive.ResponseEntityBase;
import com.darkmi.phoneremote.entity.receive.ResponseHeader;
import com.darkmi.phoneremote.tool.MessageType;


public abstract class ResponseBase {

	public ResponseBase() {
	}
	
	public ResponseBase(byte[] body) {
		this.body = body;
	}

	private byte[] body;
	private ResponseHeader rh;
	private MessageType msgType;
	
	public abstract ResponseEntityBase fillBody();

	


	
	public MessageType getMsgType() {
		return msgType;
	}

	public void setMsgType(MessageType msgType) {
		this.msgType = msgType;
	}

	public ResponseHeader getRh() {
		return rh;
	}

	public void setRh(ResponseHeader rh) {
		this.rh = rh;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}
}
