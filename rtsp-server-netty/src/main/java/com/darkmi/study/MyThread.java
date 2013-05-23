package com.darkmi.study;

public class MyThread extends Thread {
	public static int n = 0;

	public void run() {
		int m = n;
		yield();
		m++;
		n = m;
	}

	public static void main(String[] args) throws Exception {
		MyThread myThread = new MyThread();
		Thread threads[] = new Thread[100];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(myThread);
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
		System.out.println("n = " + MyThread.n);
	}
}
