package com.darkmi.server.core;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.rtsp.RtspMethods;
import io.netty.util.CharsetUtil;

import java.util.concurrent.Callable;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.darkmi.server.config.ServerConfig;
import com.darkmi.server.rtsp.AnnounceAction;
import com.darkmi.server.rtsp.GetParameterAction;
import com.darkmi.server.rtsp.PauseAction;
import com.darkmi.server.rtsp.PlayAction;
import com.darkmi.server.rtsp.SetupAction;
import com.darkmi.server.rtsp.TeardownAction;
import com.darkmi.server.session.RtspSessionAccessor;
import com.darkmi.server.session.RtspSessionKeyFactory;
import com.darkmi.server.session.SimpleRandomKeyFactory;

public class RtspController implements RtspListener {
	public static final Logger logger = Logger.getLogger(RtspController.class);
	public static final String SERVER = "RtspServer";
	public static final String REQUIRE_VALUE_NGOD_R2 = "com.comcast.ngod.r2";
	public static final String REQUIRE_VALUE_NGOD_C1 = "com.comcast.ngod.c1";

	private String ip;
	private int port;
	private ServerConfig serverConfig;
	private RtspStack server = null;
	public static final RtspSessionKeyFactory keyFactory = new SimpleRandomKeyFactory();
	@Resource
	public static RtspSessionAccessor sessionAccessor;

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
		//String OnDemandSessionId = request.headers().get("OnDemandSessionId");
		//logger.debug("OnDemandSessionId ==> " + OnDemandSessionId);
		//channelMap.put(OnDemandSessionId, channel);
		try {
			if (request.getMethod().equals(RtspMethods.SETUP)) {
				onSetupRequest(request, channel);
			} else if (request.getMethod().equals(RtspMethods.PLAY)) {
				onPlayRequest(request, channel);
			} else if (request.getMethod().equals(RtspMethods.PAUSE)) {
				onPauseRequest(request, channel);
			} else if (request.getMethod().equals(RtspMethods.GET_PARAMETER)) {
				onGetParameterRequest(request, channel);
			} else if (request.getMethod().equals(RtspMethods.TEARDOWN)) {
				onTeardownRequest(request, channel);
			}
		} catch (Exception e) {
			logger.error("Unexpected error during processing,Caused by ", e);
		}
	}

	private void onSetupRequest(HttpRequest request, Channel channel) {
		try {
			Callable<FullHttpResponse> action = new SetupAction(serverConfig, request);
			FullHttpResponse setupResponse = action.call();

			logger.debug("setup response header =====> \n" + setupResponse);
			logger.debug("setup response content =====> \n" + setupResponse.content().toString(CharsetUtil.UTF_8));
			channel.writeAndFlush(setupResponse);
		} catch (Exception e) {
			logger.error("Setup Request Handle Error.........", e);
		}
	}

	private void onPlayRequest(HttpRequest request, Channel channel) {
		try {
			Callable<HttpResponse> action = new PlayAction(request);
			HttpResponse playResponse = action.call();
			logger.debug("play response =====> \n" + playResponse);
			channel.writeAndFlush(playResponse);
		} catch (Exception e) {
			logger.error("Play Request Handle Error.........", e);
		}
	}

	private void onPauseRequest(HttpRequest request, Channel channel) {
		try {
			Callable<HttpResponse> responseAction = new PauseAction(request);
			HttpResponse pauseResponse = responseAction.call();
			logger.debug("pause response header =====> \n" + pauseResponse);

			Callable<HttpRequest> announceAction = new AnnounceAction(request);
			HttpRequest announceRequest = announceAction.call();
			logger.debug("announce request =====> \n" + announceRequest);

			channel.writeAndFlush(pauseResponse);
			channel.writeAndFlush(announceRequest);
		} catch (Exception e) {
			logger.error("Pause Request Handle Error.........", e);
		}

	}

	private void onGetParameterRequest(HttpRequest request, Channel channel) {
		try {
			Callable<HttpResponse> action = new GetParameterAction(request);
			HttpResponse response = action.call();
			logger.debug("get_parameter response =====> \n" + response);
			channel.writeAndFlush(response);
		} catch (Exception e) {
			logger.error("get_parameter Request Handle Error.........", e);
		}
	}

	private void onTeardownRequest(HttpRequest request, Channel channel) {
		try {
			Callable<HttpResponse> action = new TeardownAction(request);
			HttpResponse response = action.call();
			logger.debug("teardown response =====> \n" + response);
			channel.writeAndFlush(response);
		} catch (Exception e) {
			logger.error("teardown Request Handle Error.........", e);
		}
	}

	@Override
	public void onRtspResponse(HttpResponse response) {

	}

	/*-----------Setter And Getter --------------*/

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

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

}
