package com.darkmi.thread;

public class ThreadDemo3 {
	public static void main(String[] args) throws Exception {
		new MyThread().start();
		while (true) {
			System.out.println(Thread.currentThread().getName() + " is runing~");
			Thread.sleep(50);
		}
	}
}

class MyThread extends Thread {
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
