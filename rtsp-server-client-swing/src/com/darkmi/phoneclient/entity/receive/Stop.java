package com.darkmi.phoneclient.entity.receive;

import com.darkmi.phoneclient.tool.MessageType;

public class Stop extends ResponseEntityBase {

	public Stop(MessageType msgType) {
		super.msgType = msgType;
	}

	private ResponseHeader rh;
	private String operationCode;

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public ResponseHeader getRh() {
		return rh;
	}

	public void setRh(ResponseHeader rh) {
		this.rh = rh;
	}

}
