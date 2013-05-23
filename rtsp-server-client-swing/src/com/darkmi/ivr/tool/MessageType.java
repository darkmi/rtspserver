package com.darkmi.ivr.tool;

public enum MessageType {
	SETUP("0200"), SENDKEY("0201"), TEARDOWN("0202"), ANNOUNCE("0203"), UNKNOWN(
			"020F");

	private String messageId;

	private MessageType(String messageId) {
		this.messageId = messageId;
	}

	public String getMessageId() {
		return this.messageId;
	}

	/**
	 * 根据消息ID获取消息类型
	 * 
	 * @param messageId
	 * @return
	 */
	public static MessageType getKeyByValue(String value) {
		for (MessageType type : MessageType.values()) {
			if (type.getMessageId().equals(value)) {
				return type;
			}
		}
		return null;
	}
}
