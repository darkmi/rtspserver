package com.darkmi.ivr.entity.send;

import java.nio.ByteBuffer;

import myUtil.CommonUtil;
import myUtil.ConversionUtil;


public class RequestSendkey {

	public RequestSendkey() {
	}
	
	private String msg_ID;
	private String userId;
	private String dataContent;//按键ID
	
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
	public String getDataContent() {
		return dataContent;
	}
	public void setDataContent(String dataContent) {
//		for(dataContent.name())
		this.dataContent = dataContent;
	}


	public byte[] toBytes(){
		ByteBuffer bb = ByteBuffer.allocate(50);
		bb.put(ConversionUtil.str2byte(msg_ID,16));
		bb.put(CommonUtil.concatemer(userId, "F", 20, 0).getBytes());
		bb.put(ConversionUtil.str2byte(dataContent,16));
		bb.flip();
		byte[] b = new byte[bb.limit()];
		bb.get(b);
		return b;
	}
	
	

}
