package com.darkmi.phoneclient.tool;

import com.darkmi.OperationCode;

public enum OperationCodeEnum implements OperationCode{
	VOD播放状态("50"), VOD暂停状态("51"), 无此用户("53"), VOD系统错误("54"), 多个活跃用户("57"), 系统维护中(
			"58"), 密码错误("59"), 全部播放完成或未开始播放("5D"), 会话已经不存在("5B"), 没有登录("62"), 资源不存在(
			"52"), VOD资源不足("55"), CA系统切换频点失败("56"), 余额不足("60"), 没有订购该业务("61"), 用户机顶盒不支持此视频格式(
			"63"), 点播节目已播放完成("5A"), 非法数据("5C"), 全部播放完成("5D"), 用户在另一处登录("5E"), 操作超时(
			"5F"),未知("wz"),退出成功("65");

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
		return OperationCodeEnum.非法数据;
	}


	@Override
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
