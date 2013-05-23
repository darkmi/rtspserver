package com.darkmi.phoneremote.entity.receive;

import com.darkmi.phoneremote.tool.MessageType;

public class Announce extends ResponseEntityBase{

	public Announce(MessageType msgType) {
		super.msgType = msgType;
	}
	
	private ResponseHeader rh;
	private String msgID;
	private String operationCode;
	
	public ResponseHeader getRh() {
		return rh;
	}
	public void setRh(ResponseHeader rh) {
		this.rh = rh;
	}
	public String getMsgID() {
		return msgID;
	}
	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	
	

}
