package com.darkmi.vvs.rs.entity;


/**
 * Description: 
 * Copyright (c) 永新视博
 * All Rights Reserved.
 * @version 1.0  2012-6-5 下午04:24:31 saiwengang created
 */
public enum ContentStateEnum {
	PENDING(0), TRANSFER(1),  PLAY(2), COMPLETE(3), CANCELED(4), FAILED(5);
	public String getLabel() {
		switch (this) {
		case PENDING:
			return "pending";
		case TRANSFER:
			return "transfer";
		case PLAY:
			return "play";
		case COMPLETE:
			return "complete";
		case CANCELED:
			return "canceled";
		case FAILED:
			return "failed";
		}
		return super.toString();
	}

	private Integer value;

	private ContentStateEnum(Integer value) {

		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public static ContentStateEnum getContentStateEnumByValue(Integer value) {
		for (ContentStateEnum contentStateEnum : ContentStateEnum.values()) {
			if (contentStateEnum.getValue() == value) {
				return contentStateEnum;
			}
		}
		return null;
	}
}
