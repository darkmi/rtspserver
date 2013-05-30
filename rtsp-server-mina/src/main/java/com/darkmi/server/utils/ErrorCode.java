package com.darkmi.server.utils;

public enum ErrorCode {
	SystemError("10001"), SystemBusy("10002"), ParamError("10003"), NotSuportedMethod("10004"), ResourceNotFound(
			"10005"),

	ExistUser("20001"), WithoutCa("20002"), NotMatchPhoneAndCa("20003"), OtherRegisterError("20004"),

	WithoutUser("20101"), UserOrPasswordError("20102"), NotLogin("20103"),

	VideoPatternUnsupport("20201"), DatabaseError("20202"), UserNotBuyingProgram("20203"), WithoutOfferingId("20301");
	private String code;

	private ErrorCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public String getErrorCode() {
		return "{\"error\":\"" + getCode() + "\"}";
	}
}
