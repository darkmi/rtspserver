package com.darkmi.server.rtsp;

/**
 * Description:OPTIONS响应中的头信息定义. 
 * Copyright (c) 永新视博
 * All Rights Reserved.
 * @version 1.0  2011-3-21 下午04:38:25 MiXiaohui created.
 */
public enum ResponseHeader {
	Public("Public", "DESCRIBE, SETUP, TEARDOWN"), Server("Server", "MediaHawk"), Content_Length("Content-Length", "0");
	private final String respKey;
	private final String respValue;

	private ResponseHeader(String respKey) {
		this.respKey = respKey;
		this.respValue = null;
	}

	private ResponseHeader(String respKey, String respValue) {
		this.respKey = respKey;
		this.respValue = respValue;
	}

	public String respKey() {
		return respKey;
	}

	public String respValue() {
		return respValue;
	}
}
