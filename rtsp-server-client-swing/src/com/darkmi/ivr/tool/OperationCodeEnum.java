package com.darkmi.ivr.tool;

import com.darkmi.OperationCode;

public enum OperationCodeEnum implements OperationCode{
	VOD资源不足("24"), 点播节目已停止("26"), 点播节目成功("27"), 点播节目失败("28"), 系统错误("29"), 操作超时(
			"2A"), 余额不足("2C"), 没有订购该服务("2D"), 进入VOD界面状态("20"), 进入VOD播放状态("21"), 无此用户(
			"22"), VOD系统错误("23"), CA系统切换频点失败("25"), 多个活跃用户("2B"), 系统维护中("2E"),用户机顶盒不支持此视频格式("2F"),未知("wz");

	private String keyId;

	private OperationCodeEnum(String str) {
		this.keyId = str;
	}

	public String getMessageId() {
		return this.keyId;
	}

	/**
	 * 返回自己
	 * @return
	 */
	public static OperationCodeEnum getSelf(){
		return OperationCodeEnum.余额不足;
	}
	
	/**
	 * 根据值,得到键
	 * 
	 * @param value
	 * @return
	 */
	public String getKeyByValue(String value) {
		OperationCodeEnum[] oc = OperationCodeEnum.values();
		for (OperationCodeEnum cc : oc) {
			if (cc.getMessageId().equalsIgnoreCase(value)) {
				return cc.name();
			}
		}
		return "";

	}

}
