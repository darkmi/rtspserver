package com.darkmi.server.config;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.darkmi.server.core.RtspController;

@Configuration
@ComponentScan(basePackages = { "com.darkmi" })
@PropertySource("classpath:application.properties")
public class ServerConfig {
	private static final String PROPERTY_RTSP_SERVER_IP = "rtsp.server.ip";
	private static final String PROPERTY_RTSP_SERVER_PORT = "rtsp.server.port";

	@Autowired
	private Environment environment;

	@Bean(autowire = Autowire.BY_TYPE)
	public RtspController rtspServer() {
		String ip = environment.getProperty(PROPERTY_RTSP_SERVER_IP);
		int port = Integer.parseInt(environment.getProperty(PROPERTY_RTSP_SERVER_PORT));
		
		RtspController rtspController = null;
		try {
			rtspController = new RtspController();
			rtspController.setIp(ip);
			rtspController.setPort(port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtspController;
	}
}
