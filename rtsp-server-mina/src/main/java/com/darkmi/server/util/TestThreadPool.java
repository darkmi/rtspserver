package com.darkmi.server.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadPool {
	public static void main(String args[]) throws InterruptedException {
		ExecutorService exec = Executors.newFixedThreadPool(2);
		for (int index = 0; index < 100; index++) {
			Runnable run = new Runnable() {
				public void run() {
					long time = (long) (Math.random() * 1000);
					System.out.println("Sleeping " + time + "ms");
					try {
						Thread.sleep(time);
					} catch (InterruptedException e) {

					}
				}
			};
			exec.execute(run);
		}
		// must shutdown
		exec.shutdown();
	}
}
