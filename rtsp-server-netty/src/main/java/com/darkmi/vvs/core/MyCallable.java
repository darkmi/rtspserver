package com.darkmi.vvs.core;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

class MyCallable implements Callable<String> {
	@Override
	public String call() throws Exception {
		TimeUnit.MILLISECONDS.sleep(1);
		return "哈哈！";
	}
}
