package com.darkmi.vvs.core;

import org.jboss.netty.handler.codec.http.HttpRequest;

/**
 * 客户端和服务端都需要实现的接口.
 * @author MiXiaohui
 *
 */
public interface RtspStack {
	public String getAddress();
	public int getPort();
	public void start();
	public void stop();
	public void setRtspListener(RtspListener listener);
	public void sendRquest(HttpRequest rtspRequest, String host, int port);
}
