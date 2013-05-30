package com.darkmi.server.util;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

class GetOn implements Runnable {

	private CountDownLatch waitStudentsGetOn;

	GetOn(CountDownLatch waitStudentsGetOn) {
		this.waitStudentsGetOn = waitStudentsGetOn;
	}

	public void run() {
		for (int i = 0; i < CountDownLatchTest.numberOfPeople; i++) {
			try {
				if (CountDownLatchTest.isGone) {
					System.out.println("妈的，还差：" + waitStudentsGetOn.getCount() + " 个没娃上车呢.怎么车走了");
					break;
				}
				boolean goonSuccess = new Student(i + 1).getOn();//顺序上车
				if (goonSuccess)
					waitStudentsGetOn.countDown();
			} catch (InterruptedException e) {
			}
			if (waitStudentsGetOn.getCount() != 0l) {
				System.out.println("还差：" + (waitStudentsGetOn.getCount()) + " 个没上车");
			} else {
				System.out.println("都上车了");
			}
		}

	}

	class Student {
		private int myNum;//学生编号

		public Student(int num) {
			this.myNum = num;
		}

		//上车
		@SuppressWarnings("static-access")
		public boolean getOn() throws InterruptedException {
			Thread.currentThread().sleep(new Random().nextInt(2) * 1000);//上车使用的时间，随机
			if (CountDownLatchTest.isGone) {
				return false;//不能上了，上车失败
			}
			System.out.print("编号为:" + myNum + "的同学上车了..");
			return true;
		}
	}
}
