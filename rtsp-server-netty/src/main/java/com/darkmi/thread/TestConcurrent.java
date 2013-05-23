package com.darkmi.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestConcurrent implements Runnable {

	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 10; i++) {
			pool.execute(new TestConcurrent(i));
		}
		pool.shutdown();
	}

	private int id = 0;

	public TestConcurrent(int id) {
		this.id = id;
		System.out.println("create£º" + this.id);
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			System.out.println("this£º" + this.id);
		}
	}
}
