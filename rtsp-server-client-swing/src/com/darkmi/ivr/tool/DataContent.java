package com.darkmi.ivr.tool;

public enum DataContent {
	数字键0("B1"), 数字键1("31"), 数字键2("26"), 数字键3("33"), 数字键4("25"), 数字键5("0D"), 数字键6(
			"27"), 数字键7("37"), 数字键8("28"), 数字键9("39"), NX("1B");

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
