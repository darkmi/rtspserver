package com.darkmi.phoneremote.entity.commonEntity;

import java.util.HashMap;

public class ViewEntity {

	public ViewEntity() {
	}

//	public static Integer success = 0;//返回成功总量
	public static Integer failed = 0;//总失败数量
	public static HashMap<String,Integer> hm = new HashMap<String,Integer>();//<OperationCode,数量>
	public static Integer ConnSuccess = 0;//成功连接数量
	public static Integer Setup = 0;//setup成功数量
//	public static Integer VideoDemand = 0;//点播成功数量
	public static Integer BackPage = 0;//返回到页面成功数量
	
//	public static String userID="0";
	
	
	static{
		hm.put("60", 0);
		hm.put("61", 0);
		hm.put("6F", 0);
		hm.put("62", 0);
		hm.put("63", 0);
		hm.put("64", 0);
		hm.put("65", 0);
		hm.put("6B", 0);
		hm.put("6E", 0);
		hm.put("71", 0);
		hm.put("66", 0);
		hm.put("67", 0);
		hm.put("68", 0);
		hm.put("69", 0);
		hm.put("6A", 0);
		hm.put("6C", 0);
		hm.put("6D", 0);
		hm.put("6F", 0);
	}
	
	public static void clear(){
		ConnSuccess = 0;
//		success = 0;
		failed = 0;
		Setup=0;
		BackPage=0;
		hm.put("60", 0);
		hm.put("61", 0);
		hm.put("6F", 0);
		hm.put("62", 0);
		hm.put("63", 0);
		hm.put("64", 0);
		hm.put("65", 0);
		hm.put("6B", 0);
		hm.put("6E", 0);
		hm.put("71", 0);
		hm.put("66", 0);
		hm.put("67", 0);
		hm.put("68", 0);
		hm.put("69", 0);
		hm.put("6A", 0);
		hm.put("6C", 0);
		hm.put("6D", 0);
		hm.put("6F", 0);
	}
	
	
	/**
	 * 添加连接成功个数
	 */
	public synchronized static void addConnSuccess(){
		ConnSuccess +=1;
	}
	
	
	/**
	 * 添加返回具体状态个数
	 * @param key
	 */
	public synchronized static void addPoerationCode(String key) {
		Integer amount = hm.get(key);
		hm.put(key, amount+1);
	}

	/**
	 * 添加点播成功个数
	 */
//	public static void addSuccess() {
//		success += 1;
//	}
	/**
	 * 添加点播成功个数
	 */
	public synchronized static void addSetUp() {
		Setup += 1;
	}
	
	/**
	 * 添加点播成功个数
	 */
	public synchronized static void addBackPage() {
		BackPage += 1;
	}

	/**
	 * 添加点播失败个数
	 */
	public synchronized static void addFailed() {
		failed += 1;
	}

}
