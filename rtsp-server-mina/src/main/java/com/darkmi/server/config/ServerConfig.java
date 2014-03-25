package com.darkmi.server.config;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.darkmi.server.RtspServer;

@Configuration
@ComponentScan(basePackages = { "com.darkmi" })
@PropertySource("classpath:application.properties")
public class ServerConfig {
	private static final String PROPERTY_RTSP_SERVER_IP = "rtsp.server.ip";
	private static final String PROPERTY_RTSP_SERVER_PORT = "rtsp.server.port";

	@Autowired
	private Environment environment;

	@Bean(autowire = Autowire.BY_TYPE)
	public RtspServer rtspServer() {
		RtspServer rtspServer = new RtspServer();
		rtspServer.setIp(environment.getProperty(PROPERTY_RTSP_SERVER_IP));
		rtspServer.setPort(Integer.parseInt(environment.getProperty(PROPERTY_RTSP_SERVER_PORT)));
		return rtspServer;
	}
}
