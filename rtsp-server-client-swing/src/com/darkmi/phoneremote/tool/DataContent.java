package com.darkmi.phoneremote.tool;

public enum DataContent {
	首页("B1"), 
	上("26"), 
	左("25"), 
	确定("0D"), 
	右("27"), 
	下("28"), 
	上一级("1B"),
	上一页("21"), 
	下一页("22");

	private String keyId;

	private DataContent(String keyId) {
		this.keyId = keyId;
	}

	public String getMessageId() {
		return this.keyId;
	}

	/**
	 * 根据消息ID获取消息类型
	 * 
	 * @param value 按键
	 * @return
	 */
	public static DataContent getKeyByValue(String value) {
		for (DataContent type : DataContent.values()) {
			if (type.getMessageId().equalsIgnoreCase(value)) {
				return type;
			}
		}
		return null;
	}
}
