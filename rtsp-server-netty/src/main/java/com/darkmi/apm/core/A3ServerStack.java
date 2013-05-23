package com.darkmi.apm.core;

import org.jboss.netty.handler.codec.http.HttpRequest;


/**
 * 注入服务端接口定义.
 * @author MiXiaohui
 *
 */
public interface A3ServerStack {
	public String getAddress();
	public int getPort();
	public void start();
	public void stop();
	public void setA3Listener(A3Listener listener);
	public void sendRquest(HttpRequest a3Request, String host, int port);
}
