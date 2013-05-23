package com.darkmi.phoneclient.tool;

/**
 * Description:消息类型枚举 
 * Copyright (c) 永新视博
 * All Rights Reserved.
 * @version 1.0  2011-4-17 上午10:28:55 laojiang created
 */
public enum MessageType {
	SETUP("0500"), SENDKEY("0504"), TEARDOWN("0506"), ANNOUNCE("0507"),PLAY("0503");

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
