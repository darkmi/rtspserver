package com.darkmi.phoneremote.entity.send;

import java.nio.ByteBuffer;

import myUtil.ConversionUtil;

public class RequestStop {

	public RequestStop() {
	}
	
	private String msg_ID;

	
	public String getMsg_ID() {
		return msg_ID;
	}

	public void setMsg_ID(String msg_ID) {
		this.msg_ID = msg_ID;
	}
	
	
	
	public byte[] toBytes(){
		ByteBuffer bb = ByteBuffer.allocate(50);
		bb.put(ConversionUtil.str2byte(msg_ID,16));
		bb.flip();
		byte[] b = new byte[bb.limit()];
		bb.get(b);
		return b;
	}

}
