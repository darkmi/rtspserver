package com.darkmi.server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.darkmi.server.config.ServerConfig;

/**
 * Start RtspServer.
 * 
 * @author DarkMi
 * 
 */
public class Main {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx =
        new AnnotationConfigApplicationContext(ServerConfig.class);
    RtspServer rtspServer = (RtspServer) ctx.getBean(RtspServer.class);
    RtspMessageHandler handler = ctx.getBean(RtspMessageHandler.class);
    handler.setServerAddr(rtspServer.getIp());
    handler.setServerPort(rtspServer.getPort());
    rtspServer.setMessageHandler(handler);
    rtspServer.start();
  }
}
