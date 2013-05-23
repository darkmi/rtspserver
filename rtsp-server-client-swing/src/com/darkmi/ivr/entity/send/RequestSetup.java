package com.darkmi.ivr.entity.send;

import java.nio.ByteBuffer;

import myUtil.CommonUtil;
import myUtil.ConversionUtil;

public class RequestSetup{

	public RequestSetup() {
	}
	
	
	private String msg_ID;
	private String userId;
	


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

	
	
	public byte[] toBytes(){
		ByteBuffer bb = ByteBuffer.allocate(50);
		bb.put(ConversionUtil.str2byte(msg_ID,16));
		bb.put(CommonUtil.concatemer(userId, "F", 20, 0).getBytes());
		bb.flip();
		byte[] b = new byte[bb.limit()];
		bb.get(b);
		return b;
	}

}
