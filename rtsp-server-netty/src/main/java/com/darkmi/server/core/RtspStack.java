package com.darkmi.server.core;

import io.netty.handler.codec.http.HttpRequest;

/**
 * main interface.
 * @author darkmi
 *
 */
public interface RtspStack {
	
	public String getAddress();

	public int getPort();

	public void start();

	public void stop();

	public void setRtspListener(RtspListener listener);

	public void sendRequest(HttpRequest rtspRequest, String host, int port);
}
