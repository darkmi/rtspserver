package com.darkmi.apm.xstream;

public class PhoneNumber {
	private int code;
	private String number;

	public PhoneNumber() {
	}

	public PhoneNumber(int code, String number) {
		this.code = code;
		this.number = number;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "PhoneNumber [code=" + code + ", number=" + number + "]";
	}
}
