package com.darkmi.apm.core;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;

public interface A3Listener {
	public void onA3Request(HttpRequest request, Channel chanel);
	public void onA3Response(HttpResponse response);
}
