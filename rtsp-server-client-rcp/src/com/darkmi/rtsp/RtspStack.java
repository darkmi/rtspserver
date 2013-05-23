package com.darkmi.rtsp;

import org.jboss.netty.handler.codec.http.HttpRequest;

/**
 * 客户端及服务端共同的抽象接口.
 * @author Administrator
 *
 */
public interface RtspStack extends Runnable{
	public int getPort();

	public String getAddress();

	public void start();

	public void stop();

	public void setRtspListener(RtspListener listener);

	public void sendRquest(HttpRequest rtspRequest);
	
}
