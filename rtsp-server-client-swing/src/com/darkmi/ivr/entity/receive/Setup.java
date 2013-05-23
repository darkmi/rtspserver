package com.darkmi.ivr.entity.receive;

import com.darkmi.ivr.tool.MessageType;

/**
 * ivr的setup返回实体
 * @author Administrator
 *
 */
public class Setup extends ResponseEntityBase{

	public Setup(MessageType msgType) {
		super.msgType = msgType;
	}
	
	private ResponseHeader rh;
	private String msg_ID;
	private String userId;
	private String OperationCode;
	
	
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
		return OperationCode;
	}
	public void setOperationCode(String operationCode) {
		OperationCode = operationCode;
	}
	public ResponseHeader getRh() {
		return rh;
	}
	public void setRh(ResponseHeader rh) {
		this.rh = rh;
	}


}
