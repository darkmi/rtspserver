package com.darkmi.server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.darkmi.server.config.AppConfig;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		RtspServer rtspServer = ctx.getBean(RtspServer.class);
		rtspServer.start();
	}

}
