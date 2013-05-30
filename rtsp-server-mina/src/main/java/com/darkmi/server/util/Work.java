package com.darkmi.server.util;

import java.util.concurrent.CountDownLatch;

public class Work implements Runnable {
	private int id;
	private CountDownLatch beginSignal;
	private CountDownLatch endSignal;

	public Work(int id, CountDownLatch begin, CountDownLatch end) {
		this.id = id;
		this.beginSignal = begin;
		this.endSignal = end;
	}

	@Override
	public void run() {
		try {
			beginSignal.await();
			System.out.println("起跑...");
			System.out.println("work" + id + "到达终点");
			endSignal.countDown();
			System.out.println("work" + id + "继续干其他事情");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
