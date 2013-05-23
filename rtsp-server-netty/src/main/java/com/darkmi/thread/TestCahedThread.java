package com.darkmi.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCahedThread {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int index = 0; index < 10; index++) {
			Runnable run = new Runnable() {
				public void run() {
					long time = (long) (Math.random() * 1000);
					System.out.println("sleep:" + time + " ss ");
					try {
						Thread.sleep(time);
					} catch (Exception e) {
					}
				}
			};
			exec.execute(run);
		}
		exec.shutdown();
	}
}
