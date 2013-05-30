package com.darkmi.server.util;

import java.util.concurrent.CyclicBarrier;

public class GameBarrier {
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {

			@Override
			public void run() {
				System.out.println("所有玩家进入第二关！");
			}
		});

		for (int i = 0; i < 4; i++) {
			new Thread(new Player(i, cyclicBarrier)).start();
		}
	}
}
