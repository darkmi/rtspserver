package com.darkmi.server.util;

import java.util.Random;

public class RandowTwo {

	public static void main(String[] args) {
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			System.out.println(Math.abs(random.nextInt()) % 1000000);
		}
	}
}
