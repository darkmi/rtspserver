package com.darkmi.thread;

public class ThreadDemo4 {

	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new RunnableTest());
		t.setDaemon(true);
		t.start();
	}
}

class RunnableTest implements Runnable {

	@Override
	public void run() {
		while (true) {
			System.out.println(Thread.currentThread().getName() + " is runing~");
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
