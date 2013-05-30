package com.darkmi.server.util;

import java.util.Random;

public class RandomOne {
	public static void main(String[] args) {
		Random random1 = new Random(100);
		System.out.println(random1.nextInt());
		System.out.println(random1.nextFloat());
		System.out.println(random1.nextBoolean());
		Random random2 = new Random(100);
		System.out.println(random2.nextInt());
		System.out.println(random2.nextFloat());
		System.out.println(random2.nextBoolean());
	}
}
