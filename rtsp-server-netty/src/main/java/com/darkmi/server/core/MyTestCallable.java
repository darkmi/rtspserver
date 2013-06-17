package com.darkmi.server.core;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyTestCallable {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		Future<String> future = exec.submit(new MyCallable());
		while (!future.isDone()) {
			System.out.println("task还没有执行完成....");
		}
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
