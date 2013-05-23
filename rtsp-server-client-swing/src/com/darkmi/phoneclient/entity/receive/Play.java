package com.darkmi.phoneclient.entity.receive;

import com.darkmi.phoneclient.tool.MessageType;

public class Play extends ResponseEntityBase{

	public Play(MessageType msgType) {
		super.msgType = msgType;
	}
	
	private ResponseHeader rh;
	private String operationCode;
	private int nptBegin;
	private int nptTotal;
	
	public ResponseHeader getRh() {
		return rh;
	}
	public void setRh(ResponseHeader rh) {
		this.rh = rh;
	}
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	public int getNptBegin() {
		return nptBegin;
	}
	public void setNptBegin(int nptBegin) {
		this.nptBegin = nptBegin;
	}
	public int getNptTotal() {
		return nptTotal;
	}
	public void setNptTotal(int nptTotal) {
		this.nptTotal = nptTotal;
	}
}
