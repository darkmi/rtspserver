package com.darkmi.server.util;

import java.util.concurrent.CountDownLatch;

public class Main {
	public static void main(String[] args) {
		CountDownLatch begSignal = new CountDownLatch(1);
		CountDownLatch endSignal = new CountDownLatch(8);

		for (int i = 0; i < 8; i++) {
			new Thread(new Work(i, begSignal, endSignal)).start();
		}

		try {
			begSignal.countDown(); //统一起跑
			endSignal.await(); //等待运动员到达终点
			System.out.println("结果发送到汇报成绩的系统");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
