package com.darkmi.phoneremote.tool;

import com.darkmi.OperationCode;

public enum OperationCodeEnum implements OperationCode{
	成功VOD界面状态("60"), 
	成功VOD播放状态("61"), 
	成功VOD暂停状态("6F"), 
	失败无此用户("62"),
	VOD系统错误("63"), 
	VOD资源不足("64"), 
	CA系统切换频点失败("65"), 
	失败多个活跃用户("6B"), 
	系统维护中("6E"),
	点播节目已停止("66"),
	用户在另一处登陆("67"),
	点播节目失败("68"),
	系统错误重新连接("69"),
	操作超时("6A"),
	余额不足("6C"),
	没有订购该服务("6D"),
	暂停状态("6F"),
	用户机顶盒不支持此视频格式("71");

	private String keyId;

	private OperationCodeEnum(String str) {
		this.keyId = str;
	}

	public String getMessageId() {
		return this.keyId;
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
	
	/**
	 * 返回自己
	 * @return
	 */
	public static OperationCodeEnum getSelf(){
		return OperationCodeEnum.余额不足;
	}

}
