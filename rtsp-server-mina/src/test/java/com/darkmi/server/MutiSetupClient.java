package com.darkmi.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 并发测试TEARDOWN
 * @author MiXiaohui
 *
 */
public class MutiSetupClient {
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		for (int i = 1; i < 5; i++) {
			threadPool.execute(new Runnable() {
				public void run() {
					SetupRequestAction.send();
				}
			});
		}
		threadPool.shutdown();// 任务执行完毕，关闭线程池
	}
}
