package com.darkmi.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
*代理类一定要实现了InvocationHandler接口
*/
public class ProxyObject implements InvocationHandler {
	private Object proxy_obj;

	ProxyObject(Object obj) {
		this.proxy_obj = obj;
	}

	public static Object factory(Object obj) {
		Class cls = obj.getClass();
		//通过Proxy类的newProxyInstance方法来返回代理对象
		return Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), new ProxyObject(obj));
	}

	/**
	*实现InvocationHandler接口的invoke
	*/
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("函数调用前被拦截了:   " + method);
		if (args != null) {
			//打印参数列表
			System.out.println("方法有  " + args.length + "    个参数");
			for (int i = 0; i < args.length; i++) {
				System.out.println(args[i]);
			}
		}
		//利用反射机制动态调用原对象的方法
		Object mo = method.invoke(proxy_obj, args);
		System.out.println("函数调用后进行处理 :   " + method);
		return mo;
	}

	//测试代码    
	public static void main(String agr[]) {
		SellInterface si = (SellInterface) factory(new RedWineFactory());
		si.sell();
	}
}
