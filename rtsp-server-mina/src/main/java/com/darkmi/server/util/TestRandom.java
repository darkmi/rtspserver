package com.darkmi.server.util;

import java.util.Random;

class TestRandom {
	public static void main(String[] args) {
		int[] intRet = new int[6];
		int intRd = 0; //存放随机数
		int count = 0; //记录生成的随机数个数
		int flag = 0; //是否已经生成过标志
		while (count < 6) {
			Random rdm = new Random(System.currentTimeMillis());
			intRd = Math.abs(rdm.nextInt()) % 32 + 1;
			for (int i = 0; i < count; i++) {
				if (intRet[i] == intRd) {
					flag = 1;
					break;
				} else {
					flag = 0;
				}
			}
			if (flag == 0) {
				intRet[count] = intRd;
				count++;
			}
		}
		for (int t = 0; t < 6; t++) {
			System.out.println(t + "->" + intRet[t]);
		}
	}
}
