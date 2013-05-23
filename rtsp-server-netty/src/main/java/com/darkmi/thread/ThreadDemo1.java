package com.darkmi.thread;

public class ThreadDemo1 {

	public static void main(String[] args) {
		new TestThread().start();

		while (true) {
			System.out.println("----------- MainThread ------------");
			System.out.println(Thread.currentThread().getId());
			System.out.println(Thread.currentThread().getName());
			System.out.println(Thread.currentThread().getState());
			System.out.println(Thread.currentThread().getPriority());
			System.out.println(Thread.currentThread().getThreadGroup().getName());
			System.out.println("----------- MainThread ------------");
		}
	}
}

class TestThread extends Thread{
	

	public void run() {
		while (true) {
			System.out.println("----------- TestThread ------------");
			System.out.println(Thread.currentThread().getId());
			System.out.println(Thread.currentThread().getName());
			System.out.println(Thread.currentThread().getState());
			System.out.println(Thread.currentThread().getPriority());
			System.out.println(Thread.currentThread().getThreadGroup().getName());
			System.out.println("----------- TestThread ------------");
			
		}
	}
}
