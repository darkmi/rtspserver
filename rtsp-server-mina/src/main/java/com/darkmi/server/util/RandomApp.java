package com.darkmi.server.util;

import java.util.Random;

public class RandomApp {
	public static void main(String args[]) {
		Random ran1 = new Random();
		Random ran2 = new Random(12345);
		//创建了两个类Random的对象。
		System.out.println("The 1st set of random numbers:");
		System.out.println(" Integer:" + ran1.nextInt());
		System.out.println(" Long:" + ran1.nextLong());
		System.out.println(" Float:" + ran1.nextFloat());
		System.out.println(" Double:" + ran1.nextDouble());
		System.out.println(" Gaussian:" + ran1.nextGaussian());
		//产生各种类型的随机数
		System.out.print("The 2nd set of random numbers:");
		for (int i = 0; i < 5; i++) {
			System.out.println(ran2.nextInt() + "\n");
		}
	}
}
