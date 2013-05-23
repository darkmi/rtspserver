package com.darkmi.phoneclient.tool;

public enum DataContent {
	快退("0A"), 
	播放("0B"), 
	暂停("0C"), 
	快进("0D");

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
