package com.darkmi.server.core;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.rtsp.RtspMethods;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.darkmi.server.config.ServerConfig;

public class RtspController implements RtspListener {
	public static final Logger logger = Logger.getLogger(RtspController.class);
	public static final String SERVER = "RtspServer";
	private String ip;
	private int port;
	private ServerConfig serverConfig;
	private RtspStack server = null;
	private final Map<String, Channel> channelMap = new ConcurrentHashMap<String, Channel>();

	public void start() throws Exception {
		this.server = new RtspServerStackImpl(ip, port);
		this.server.setRtspListener(this);
		this.server.start();
		logger.debug("Started Rtsp Server. ");
	}

	public void stop() {
		this.server.stop();
	}

	@Override
	public void onRtspRequest(HttpRequest request, Channel channel) {
		logger.debug("Receive request ==> \n " + request);
		String OnDemandSessionId = request.headers().get("OnDemandSessionId");
		logger.debug("OnDemandSessionId ==> " + OnDemandSessionId);
		channelMap.put(OnDemandSessionId, channel);
		try {
			if (request.getMethod().equals(RtspMethods.SETUP)) {
				onSetupRequest((FullHttpRequest) request);
			} else if (request.getMethod().equals(RtspMethods.TEARDOWN)) {
				onTeardownRequest(request);
			}
		} catch (Exception e) {
			logger.error("Unexpected error during processing,Caused by ", e);
		}
	}

	private void onSetupRequest(FullHttpRequest request) {
		try {
			//			Callable<DefaultFullHttpRequest> action = new SetupRequestAction(request, odrmConfig);
			//			DefaultFullHttpRequest setupRequest = action.call();
			//			logger.debug("setup request header =====> \n" + setupRequest);
			//			logger.debug("setup request content =====> \n" + setupRequest.content().toString(CharsetUtil.UTF_8));
			//			client.sendRequest(setupRequest, odrmConfig.getVsIp(),
			//					odrmConfig.getVsPort());
		} catch (Exception e) {
			logger.error("SETUP 请求发送失败!!!!!!!!!", e);
		}
	}

	private void onTeardownRequest(HttpRequest request) {
		try {
			//			Callable<DefaultFullHttpRequest> action = new TeardownRequestAction(request);
			//			DefaultFullHttpRequest teardownRequest = action.call();
			//			logger.debug("teardown request header =====> \n" + teardownRequest);
			//			logger.debug("teardown request content =====> \n" + teardownRequest.content().toString(CharsetUtil.UTF_8));
			//			client.sendRequest(teardownRequest, odrmConfig.getVsIp(),
			//					odrmConfig.getVsPort());
		} catch (Exception e) {
			logger.error("TEARDOWN 请求发送失败!!!!!!!!!", e);
		}

	}

	@Override
	public void onRtspResponse(HttpResponse response) {

	}

	/*-----------Setter And Getter --------------*/

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public ServerConfig getServerConfig() {
		return serverConfig;
	}

	public void setServerConfig(ServerConfig serverConfig) {
		this.serverConfig = serverConfig;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
