package com.darkmi.phoneremote.entity.send;

import java.nio.ByteBuffer;

import myUtil.ConversionUtil;


public class RequestSendkey {

	public RequestSendkey() {
	}
	
	private String msg_ID;
	private String frequency;
	private String dataContent;//按键ID
	
	public String getMsg_ID() {
		return msg_ID;
	}
	public void setMsg_ID(String msg_ID) {
		this.msg_ID = msg_ID;
	}

	public String getDataContent() {
		return dataContent;
	}
	public void setDataContent(String dataContent) {
//		for(dataContent.name())
		this.dataContent = dataContent;
	}

	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	
	public byte[] toBytes(){
		ByteBuffer bb = ByteBuffer.allocate(50);
		bb.put(ConversionUtil.str2byte(msg_ID,16));
		bb.put(ConversionUtil.str2byte(dataContent,16));
		bb.put(ConversionUtil.int2bytes(Integer.parseInt(frequency),1));
		bb.flip();
		byte[] b = new byte[bb.limit()];
		bb.get(b);
		return b;
	}
	
	

}
