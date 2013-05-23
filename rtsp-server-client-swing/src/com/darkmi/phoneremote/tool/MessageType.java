package com.darkmi.phoneremote.tool;

/**
 * Description:消息类型枚举 
 * Copyright (c) 永新视博
 * All Rights Reserved.
 * @version 1.0  2011-4-17 上午10:28:55 laojiang created
 */
public enum MessageType {
	SETUP("0600"), SENDKEY("0601"), TEARDOWN("0602"), ANNOUNCE("0603"), NETTEST("0604"), MORECUSTOMER("0605"), UNKNOWN(
			"060F");

	private String messageId;

	private MessageType(String messageId) {
		this.messageId = messageId;
	}

	public String getMessageId() {
		return this.messageId;
	}

	/**
	 * 根据消息ID获取消息类型
	 * @param messageId
	 * @return
	 */
	public static MessageType getMessageTypeById(String messageId) {
		for (MessageType type : MessageType.values()) {
			if (type.getMessageId().equals(messageId)) {
				return type;
			}
		}
		return null;
	}
}
