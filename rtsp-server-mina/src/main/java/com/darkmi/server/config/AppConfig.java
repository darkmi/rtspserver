package com.darkmi.server.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.darkmi.server.RtspServer;

/**
 * An application context Java configuration class.
 * @author Petri Kainulainen
 */
@Configuration
@ComponentScan(basePackages = { "com.darkmi" })
@PropertySource("classpath:application.properties")
public class AppConfig {
	private static final String PROPERTY_RTSP_SERVER_IP = "rtsp.server.ip";
	private static final String PROPERTY_RTSP_SERVER_SETUP_PORT = "rtsp.server.setup.port";
	private static final String PROPERTY_RTSP_SERVER_PLAY_PORT = "rtsp.server.play.port";

	@Resource
	private Environment environment;

	@Bean
	public RtspServer rtspServer() {
		RtspServer rtspServer = new RtspServer();
		rtspServer.setIp(environment.getRequiredProperty(PROPERTY_RTSP_SERVER_IP));
		rtspServer.setSetupPort(Integer.parseInt(environment.getRequiredProperty(PROPERTY_RTSP_SERVER_SETUP_PORT)));
		rtspServer.setPlayPort(Integer.parseInt(environment.getRequiredProperty(PROPERTY_RTSP_SERVER_PLAY_PORT)));
		return rtspServer;
	}

}
