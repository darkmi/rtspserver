package com.darkmi;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;

public class PrintLog {

	public PrintLog() {
	}

	private static Logger ivr = Logger.getLogger("ivr");
	private static Logger phoneremote = Logger.getLogger("phoneremote");
	private static Logger phoneclient = Logger.getLogger("phoneclient");

	// private static Lock lock = new ReentrantLock();

	// public static void setupLog() {
	// print("IVR-SETUP");
	// }

	// public static void videoDemandLog() {
	// print("IVR-点播");
	// }

	private static final Lock lock = new ReentrantLock();

	public static void print(String type, LogEntity le, OperationCode oc,
			String file, int TestAmount) {
		lock.lock();
		synchronized (le) {

			// 不够补未知
			int aa = TestAmount - (le.getSuccess() + le.getFailed());
			if (aa > 0) {
				for (int i = 0; i < aa; i++) {
					le.addFailed();
					le.addPoerationCode("wz");
				}
			}

			StringBuilder sb = new StringBuilder();
			sb.append("\r\n");
			sb.append("##########" + type + "测试####################");
			sb.append("\r\n");
			sb.append("成功-------------------------  " + le.getSuccess() + " 个");
			sb.append("\r\n");
			sb.append("失败：---------------------------  " + le.getFailed()
					+ " 个");
			sb.append("\r\n");
			sb.append("最大响应时间：---------------------------  " + le.getMaximum()
					+ "毫秒");
			sb.append("\r\n");
			sb.append("最小响应时间：---------------------------  " + le.getMinimum()
					+ "毫秒");
			sb.append("\r\n");
			sb.append("平均响应时间：---------------------------  " + le.getaverage()
					+ "毫秒");
			sb.append("\r\n");
			sb.append("其中");
			sb.append("\r\n");
			HashMap<String, Integer> hm = le.getHM();
			Set<String> keyset = hm.keySet();
			for (String st : keyset) {
				String ss = oc.getKeyByValue(st);
				sb.append(st+"----"+ss + "------------------------  " + hm.get(st) + " 个");
				sb.append("\r\n");
			}
			sb.append("------------------------------------------------------------------");
			sb.append("\r\n");
			sb.append("\r\n");
			sb.append("\r\n");
			if ("ivr".equalsIgnoreCase(file)) {
				ivr.info(sb.toString());
			} else if ("phoneremote".equalsIgnoreCase(file)) {
				phoneremote.info(sb.toString());
			} else if ("phoneclient".equalsIgnoreCase(file)) {
				phoneclient.info(sb.toString());
			}

			// StringBuilder sb1 = new StringBuilder();
			// sb1.append("##########"+type+"测试####################");
			// sb1.append("\r\n");
			// sb1.append("成功"+le.getSuccess()+"个");
			// sb1.append("\r\n");
			// sb1.append("失败： " + le.getFailed() + "个");
			// sb1.append("\r\n");
			// sb1.append("最大响应时间： " + le.getMaximum() + "毫秒");
			// sb1.append("\r\n");
			// sb1.append("最小响应时间:" + le.getMinimum() + "毫秒");
			// sb1.append("\r\n");
			// sb1.append("平均响应时间： " + le.getaverage() + "毫秒");
			// sb1.append("\r\n");
			// sb1.append("\r\n");
			// sb1.append("\r\n");
			// Test.printResult(sb1.toString());

			// le.clear();
			lock.unlock();
		}
	}

}
