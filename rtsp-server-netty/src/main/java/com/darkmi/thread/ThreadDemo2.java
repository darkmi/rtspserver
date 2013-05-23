package com.darkmi.thread;

public class ThreadDemo2 {

	public static void main(String[] args) {
		ThreadTest tt = new ThreadTest();
		new Thread(tt).start();
		new Thread(tt).start();
		new Thread(tt).start();
		new Thread(tt).start();
	}

}

class ThreadTest implements Runnable {
	private int count = 100;

	public void run() {
		while (true) {
			if (count > 0) {
				System.out.println(Thread.currentThread().getName() + ":" + count--);
			}
		}

	}
}
