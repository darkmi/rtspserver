package com.darkmi.phoneclient.entity.receive;

import com.darkmi.phoneclient.tool.MessageType;


public class Sendkey extends ResponseEntityBase{

	public Sendkey(MessageType msgType) {
		super.msgType = msgType;
	}
	
	private int nptBegin;
	private String OperationCode;
	private int Scale;
	private ResponseHeader rh;



	public ResponseHeader getRh() {
		return rh;
	}
	public void setRh(ResponseHeader rh) {
		this.rh = rh;
	}
	public int getNptBegin() {
		return nptBegin;
	}
	public void setNptBegin(int nptBegin) {
		this.nptBegin = nptBegin;
	}
	public String getOperationCode() {
		return OperationCode;
	}
	public void setOperationCode(String operationCode) {
		OperationCode = operationCode;
	}
	public int getScale() {
		return Scale;
	}
	public void setScale(int scale) {
		Scale = scale;
	}
	
	
}
