package com.darkmi.ivr.entity.receive;

import com.darkmi.ivr.tool.MessageType;


/**
 * ivr系统返回的实体
 * @author Administrator
 *
 */
public class Announce extends ResponseEntityBase{

	public Announce(MessageType msgType) {
		super.msgType = msgType;
	}
	
	
	private ResponseHeader rh;
	private String msg_ID;
	private String userId;
	private String operationCode;
	
	
	
	
	public ResponseHeader getRh() {
		return rh;
	}
	public void setRh(ResponseHeader rh) {
		this.rh = rh;
	}
	public String getMsg_ID() {
		return msg_ID;
	}
	public void setMsg_ID(String msg_ID) {
		this.msg_ID = msg_ID;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	
	
	

}
