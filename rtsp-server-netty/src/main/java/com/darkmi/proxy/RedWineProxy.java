package com.darkmi.proxy;

/**
*代理主题角色，这里指红酒代理商。它除了也要实现了sellInterface接口外，还持有红酒
*厂商RedWineFactory 对象的引用，从而使它能在调用真实主题前后做一些必要处理。
*/
public class RedWineProxy implements SellInterface {
	//持有一个RedWineFactory对象的引用
	private RedWineFactory redWineFactory;

	//销售总量
	private static int sell_count = 0;

	public Object sell() {
		if (checkUser()) {//在通过代理主题角色，我们可以在真实主题角色被调用前做一些诸如权限判断的事情
			Object obj = redWineFactory.sell();
			sell_count++;//同样，在调用后我们也可以执行一些额外的动作。
			return obj;
		} else {
			throw new RuntimeException();
		}
	}

	protected boolean checkUser() {
		//do something
		return true;
	}

	public static void main(String agr[]) {
		SellInterface sell = new RedWineProxy();
		sell.sell();
	}
}
