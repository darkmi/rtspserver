package com.darkmi.proxy;

public class RedWineFactory implements SellInterface {
	public Object sell() {
		System.out.println("真实主题角色RedWineFactory 被调用了");
		return new Object();
	}
}
