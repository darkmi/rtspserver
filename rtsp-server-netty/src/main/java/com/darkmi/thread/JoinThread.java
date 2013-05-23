package com.darkmi.thread;

public class JoinThread {

	public static void main(String[] args) throws Exception {
		JoinThreadTest t = new JoinThreadTest();
		Thread thread = new Thread(t);
		thread.start();
		int i = 0;
		while (true) {
			if (i == 100) {
				try {
					thread.join();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			Thread.sleep(500);
			System.out.println(Thread.currentThread().getName() + " --> " + i++);
		}
	}
}

class JoinThreadTest implements Runnable {

	@Override
	public void run() {
		int i = 0;
		while (true) {
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println(Thread.currentThread().getName() + " --> " + i++);
		}

	}
}
