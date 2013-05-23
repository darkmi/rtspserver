package com.darkmi;

import java.util.HashMap;
import java.util.LinkedList;

public class LogEntity {

	public LogEntity(int type) {
		switch (type) {
		case 1:// ivr
			hm = new HashMap<String, Integer>();
			hm.put("24", 0);
			hm.put("26", 0);
			hm.put("27", 0);
			hm.put("28", 0);
			hm.put("29", 0);
			hm.put("2A", 0);
			hm.put("2C", 0);
			hm.put("2D", 0);
			hm.put("20", 0);
			hm.put("21", 0);
			hm.put("22", 0);
			hm.put("23", 0);
			hm.put("25", 0);
			hm.put("2B", 0);
			hm.put("2E", 0);
			hm.put("2F", 0);
			hm.put("wz", 0);
			break;
		case 2:// phoneclient
			hm = new HashMap<String, Integer>();
			hm.put("50", 0);
			hm.put("51", 0);
			hm.put("53", 0);
			hm.put("54", 0);
			hm.put("57", 0);
			hm.put("58", 0);
			hm.put("59", 0);
			hm.put("5D", 0);
			hm.put("5B", 0);
			hm.put("62", 0);
			hm.put("52", 0);
			hm.put("55", 0);
			hm.put("56", 0);
			hm.put("60", 0);
			hm.put("61", 0);
			hm.put("63", 0);
			hm.put("5A", 0);
			hm.put("5C", 0);
			hm.put("5D", 0);
			hm.put("5E", 0);
			hm.put("5F", 0);
			hm.put("wz", 0);
			hm.put("65", 0);
			break;
		case 3:
			hm = new HashMap<String, Integer>();
			hm.put("60", 0);
			hm.put("61", 0);
			hm.put("6F", 0);
			hm.put("62", 0);
			hm.put("63", 0);
			hm.put("64", 0);
			hm.put("65", 0);
			hm.put("6B", 0);
			hm.put("6E", 0);
			hm.put("66", 0);
			hm.put("67", 0);
			hm.put("68", 0);
			hm.put("69", 0);
			hm.put("6A", 0);
			hm.put("6C", 0);
			hm.put("6D", 0);
			hm.put("6F", 0);
			hm.put("71", 0);
			hm.put("71", 0);
			hm.put("wz", 0);
			break;

		default:
			break;
		}
	}

	private Integer success = 0;// 返回成功总量
	private Integer failed = 0;// 总失败数量
	private HashMap<String, Integer> hm = null;// <OperationCode,数量>
	private LinkedList<Long> list = new LinkedList<Long>();// 储存响应时间

	/**
	 * 得到具体返回状态代码集合
	 * 
	 * @return
	 */
	public HashMap<String, Integer> getHM() {
		return hm;
	}

	// /**
	// * 清除计数器
	// */
	// public void clear() {
	// list.clear();
	// success = 0;
	// failed = 0;
	// // maximum = 0;
	// // minimum = 0;
	// // average = 0;
	// hm.put("24", 0);
	// hm.put("26", 0);
	// hm.put("27", 0);
	// hm.put("28", 0);
	// hm.put("29", 0);
	// hm.put("2A", 0);
	// hm.put("2C", 0);
	// hm.put("2D", 0);
	// hm.put("20", 0);
	// hm.put("21", 0);
	// hm.put("22", 0);
	// hm.put("23", 0);
	// hm.put("25", 0);
	// hm.put("2B", 0);
	// hm.put("2E", 0);
	// hm.put("2F", 0);
	// }

	/**
	 * 得到成功数
	 * 
	 * @return
	 */
	public Integer getSuccess() {
		return success;
	}

	/**
	 * 得到失败数
	 * 
	 * @return
	 */
	public Integer getFailed() {
		return failed;
	}

	/**
	 * 添加返回具体状态个数
	 * 
	 * @param key
	 */
	public void addPoerationCode(String key) {
		Integer amount = hm.get(key);
		hm.put(key, amount + 1);
	}

	/**
	 * 添加响应时间
	 * 
	 * @param 时间
	 */
	public  void addResponseTime(Long time) {
		if (list.size() < 1) {
			list.add(time);
			return;
		}
		long max = list.get(0);
		long min = list.get(list.size() - 1);
		if (time >= max) {
			list.addFirst(time);
			return;
		} else if (time <= min) {
			list.addLast(time);
			return;
		} else {
			list.add(1, time);
		}
	}

	/**
	 * 得到最大响应时间
	 * 
	 * @return
	 */
	public Long getMaximum() {
		try {
			return list.get(0);

		} catch (Exception e) {
			return 0l;
		}
	}

	/**
	 * 得到最小响应时间
	 * 
	 * @return
	 */
	public Long getMinimum() {
		try {
			return list.get(list.size() - 1);

		} catch (Exception e) {
			return 0l;
		}
	}

	/**
	 * 得到平均响应时间
	 * 
	 * @return
	 */
	public Long getaverage() {
		try {
			int size = list.size();
			Long sum = 0l;
			for (int i = 0; i < size; i++) {
				sum += list.get(i);
			}
			return sum / size;

		} catch (Exception e) {
			return 0l;
		}
	}

	/**
	 * 添加点播成功个数
	 */
	public void addSuccess() {
		success += 1;
	}

	/**
	 * 添加点播失败个数
	 */
	public void addFailed() {
		failed += 1;
	}

}
