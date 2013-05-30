package com.darkmi.server.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Player implements Runnable {

	private CyclicBarrier cyclicBarrier;
	private int id;

	public Player(int id, CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			System.out.println("玩家" + id + "正在玩第一关...");
			cyclicBarrier.await();
			System.out.println("玩家" + id + "进入第二关...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}
