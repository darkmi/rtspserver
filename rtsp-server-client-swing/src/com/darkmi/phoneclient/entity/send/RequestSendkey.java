package com.darkmi.phoneclient.entity.send;

import java.nio.ByteBuffer;

import com.darkmi.phoneclient.entity.receive.ResponseEntityBase;
import com.darkmi.phoneclient.entity.receive.ResponseHeader;

import myUtil.ConversionUtil;


public class RequestSendkey extends ResponseEntityBase{

	public RequestSendkey() {
	}
	
	private int nptBegin;
	private String dataContent;
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
	public String getDataContent() {
		return dataContent;
	}
	public void setDataContent(String dataContent) {
//		for(dataContent.name())
		this.dataContent = dataContent;
	}

	
	
	public byte[] toBytes(){
		ByteBuffer bb = ByteBuffer.allocate(50);
		bb.put(ConversionUtil.str2byte(dataContent,16));
		bb.put(ConversionUtil.int2bytes(nptBegin,2));
		bb.flip();
		byte[] b = new byte[bb.limit()];
		bb.get(b);
		return b;
	}
	
	

}
