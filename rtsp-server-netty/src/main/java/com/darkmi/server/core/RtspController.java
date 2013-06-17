package com.darkmi.server.core;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.rtsp.RtspHeaders;
import org.jboss.netty.handler.codec.rtsp.RtspMethods;
import org.jboss.netty.handler.codec.rtsp.RtspResponseStatuses;
import org.jboss.netty.handler.codec.rtsp.RtspVersions;
import org.springframework.beans.factory.annotation.Autowired;

import com.darkmi.server.rtsp.AnnounceAction;
import com.darkmi.server.rtsp.DescribeAction;
import com.darkmi.server.rtsp.GetParameterAction;
import com.darkmi.server.rtsp.OptionsAction;
import com.darkmi.server.rtsp.PauseAction;
import com.darkmi.server.rtsp.PlayAction;
import com.darkmi.server.rtsp.SetupAction;
import com.darkmi.server.rtsp.TeardownAction;
import com.darkmi.server.session.RtspSessionAccessor;
import com.darkmi.server.session.RtspSessionKeyFactory;
import com.darkmi.server.session.SimpleRandomKeyFactory;


/**
 * 视频服务器启动类.
 * @author MiXiaohui.
 *
 */
public class RtspController implements RtspListener {
	private static final Logger logger = Logger.getLogger(RtspController.class);
	private String bindAddress;
	private int rmPort;
	private int smPort;

	private RtspServerStackImpl rm = null;
	private RtspServerStackImpl sm = null;
	private RtspSessionAccessor sessionAccessor;

	private static RtspSessionKeyFactory keyFactory = new SimpleRandomKeyFactory();
	public static final String SERVER = "MediaHawk";

	/**
	 * 创建.
	 */
	public void create() {
		logger.debug("Starting RTSP Controller module for VideoServer");
	}

	/**
	 * 启动.
	 * @throws Exception
	 */
	public void start() throws Exception {
		this.rm = new RtspServerStackImpl(this.bindAddress, this.rmPort);
		this.rm.setRtspListener(this);
		this.rm.start();
		logger.debug("Started RM of Video Server. Bound at IP " + this.bindAddress + " at port " + this.rmPort);

		this.sm = new RtspServerStackImpl(this.bindAddress, this.smPort);
		this.sm.setRtspListener(this);
		this.sm.start();
		logger.debug("Started SM of Video Server. Bound at IP " + this.bindAddress + " at port " + this.rmPort);
	}

	/**
	 * 停止.
	 */
	public void stop() {
		logger.debug("Stop Video Server. Listening at IP " + this.bindAddress);
		this.rm.stop();
		this.sm.stop();
	}

	/**
	 * 销毁.
	 */
	public void destroy() {
		logger.debug("Stopped RTSP Controller module for VideoServer");
	}

	/**
	 * 请求分发.
	 */
	@Override
	public void onRtspRequest(HttpRequest request, Channel channel) {
		logger.debug("Receive request " + request);
		Callable<HttpResponse> action = null;
		Callable<HttpRequest> announceAction = null;
		HttpResponse response = null;
		HttpRequest announce = null;
		try {
			if (request.getMethod().equals(RtspMethods.OPTIONS)) {
				action = new OptionsAction(request);
				response = action.call();
			} else if (request.getMethod().equals(RtspMethods.DESCRIBE)) {
				action = new DescribeAction(request);
				response = action.call();
			} else if (request.getMethod().equals(RtspMethods.SETUP)) {
				InetSocketAddress inetSocketAddress = (InetSocketAddress) channel.getRemoteAddress();
				String remoteIp = inetSocketAddress.getAddress().getHostAddress();
				action = new SetupAction(this, request, remoteIp);
				response = action.call();
			} else if (request.getMethod().equals(RtspMethods.PLAY)) {
				action = new PlayAction(this, request);
				response = action.call();
			} else if (request.getMethod().equals(RtspMethods.PAUSE)) {
				action = new PauseAction(this, request);
				response = action.call();
				announceAction = new AnnounceAction(this, response);
				announce = announceAction.call();

			} else if (request.getMethod().equals(RtspMethods.GET_PARAMETER)) {
				action = new GetParameterAction(this, request);
				response = action.call();
			} else if (request.getMethod().equals(RtspMethods.TEARDOWN)) {
				action = new TeardownAction(this, request);
				response = action.call();
			} else {
				response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.METHOD_NOT_ALLOWED);
				response.setHeader(HttpHeaders.Names.SERVER, RtspController.SERVER);
				response.setHeader(RtspHeaders.Names.CSEQ, request.getHeader(RtspHeaders.Names.CSEQ));
				response.setHeader(RtspHeaders.Names.ALLOW, OptionsAction.OPTIONS);
			}
		} catch (Exception e) {
			logger.error("Unexpected error during processing,Caused by ", e);
			response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.INTERNAL_SERVER_ERROR);
			response.setHeader(HttpHeaders.Names.SERVER, RtspController.SERVER);
			response.setHeader(RtspHeaders.Names.CSEQ, request.getHeader(RtspHeaders.Names.CSEQ));
		}
		logger.debug("Sending Response: \n" + response.toString());
		channel.write(response);
		if (null != announce) {
			logger.debug("Sending Announce " + announce.toString());
			channel.write(announce);
		}
	}

	@Override
	public void onRtspResponse(HttpResponse response) {
	}

	/*-----------Setter And Getter --------------*/

	public RtspSessionAccessor getSessionAccessor() {
		return sessionAccessor;
	}

	public static RtspSessionKeyFactory getKeyFactory() {
		return keyFactory;
	}

	public int getRmPort() {
		return rmPort;
	}

	public int getSmPort() {
		return smPort;
	}

	public String getBindAddress() {
		return bindAddress;
	}

	/*----------- 注入 --------------*/

	@Autowired
	public void setSessionAccessor(RtspSessionAccessor sessionAccessor) {
		this.sessionAccessor = sessionAccessor;
	}

	@Autowired
	public void setBindAddress(String bindAddress) throws UnknownHostException {
		this.bindAddress = bindAddress;
	}

	@Autowired
	public void setRmPort(int rmPort) {
		this.rmPort = rmPort;
	}

	@Autowired
	public void setSmPort(int smPort) {
		this.smPort = smPort;
	}
}
