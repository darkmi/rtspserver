package com.darkmi.server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.darkmi.server.config.ServerConfig;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ServerConfig.class);
		RtspServer rtspServer = (RtspServer) ctx.getBean(RtspServer.class);
		rtspServer.setMessageHandler(ctx.getBean(RtspMessageHandler.class));
		rtspServer.start();
	}
}
