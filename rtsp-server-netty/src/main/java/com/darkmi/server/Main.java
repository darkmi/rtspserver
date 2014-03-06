package com.darkmi.server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.darkmi.server.config.ServerConfig;
import com.darkmi.server.core.RtspController;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ServerConfig.class);
		RtspController rtspController = (RtspController) ctx.getBean(RtspController.class);
		try {
			rtspController.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//RtspMessageHandler handler = ctx.getBean(RtspMessageHandler.class);
		//handler.setIp(rtspServer.getIp());
		//handler.setPlayPort(rtspServer.getPlayPort());
		//rtspServer.setMessageHandler(handler);
		//rtspServer.start();
	}
}
