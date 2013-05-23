package com.darkmi.phoneremote.entity.receive;

import com.darkmi.phoneremote.tool.MessageType;


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
	private String msgID;
	private String scale;
	private String operationCode;
	
	
	


	public String getMsgID() {
		return msgID;
	}
	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
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
