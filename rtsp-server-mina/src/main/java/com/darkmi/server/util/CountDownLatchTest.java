package com.darkmi.server.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
	public static int numberOfPeople = 10;//等车的学生数
	public static boolean isGone = false;//车开的标志
	public static int carWaitTime = 3;//车等的时间

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch waitStudentsGetOn = new CountDownLatch(numberOfPeople);
		new Thread(new GetOn(waitStudentsGetOn)).start();
		waitStudentGetOn(waitStudentsGetOn);//等所有的学生上车
		driveHome();//开车走
	}

	private static void waitStudentGetOn(CountDownLatch waitStudentsGetOn) throws InterruptedException {
		System.out.println("赶紧的,抓紧时间上车..");
		waitStudentsGetOn.await(carWaitTime, TimeUnit.SECONDS);//等5秒，还没上车，就开走。。
	}

	private static void driveHome() throws InterruptedException {
		System.out.println("开车，鞋儿破 帽儿破 身上的袈裟破 你笑我 他笑我 一把扇儿破");
		isGone = true;
	}
}
