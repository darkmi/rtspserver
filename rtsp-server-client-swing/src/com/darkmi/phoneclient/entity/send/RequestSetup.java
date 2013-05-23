package com.darkmi.phoneclient.entity.send;

import java.nio.ByteBuffer;

import myUtil.ConversionUtil;


public class RequestSetup {

	public RequestSetup() {
	}
	
	private String smartCartdID;
	private String type;
	private int isPassword=1;//按键ID
	private String password;//按键ID
	



	public String getSmartCartdID() {
		return smartCartdID;
	}
	public void setSmartCartdID(String smartCartdID) {
		this.smartCartdID = smartCartdID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getIsPassword() {
		return isPassword;
	}
	public void setIsPassword(int isPassword) {
		this.isPassword = isPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	public byte[] toBytes(){
		ByteBuffer bb = ByteBuffer.allocate(50);
		bb.put(smartCartdID.getBytes());
		bb.put(ConversionUtil.str2byte(type,16));
		bb.put(ConversionUtil.int2bytes(isPassword,1));
		bb.put(password.getBytes());
		bb.flip();
		byte[] b = new byte[bb.limit()];
		bb.get(b);
		return b;
	}
	
	

}
