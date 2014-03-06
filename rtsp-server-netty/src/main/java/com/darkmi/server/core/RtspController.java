package com.darkmi.server.core;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.netty.handler.codec.rtsp.RtspMethods;
import io.netty.handler.codec.rtsp.RtspResponseStatuses;
import io.netty.handler.codec.rtsp.RtspVersions;

import java.net.UnknownHostException;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

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
 * 
 * @author darkmi
 *
 */
public class RtspController implements RtspListener {
	private static final Logger logger = Logger.getLogger(RtspController.class);
	private static RtspSessionKeyFactory keyFactory = new SimpleRandomKeyFactory();
	public static final String SERVER = "MediaHawk";

	private String bindAddress;
	private int rmPort;
	private RtspStack server = null;
	private RtspSessionAccessor sessionAccessor;
	
	//private int smPort;
	//private RtspServerStackImpl sm = null;

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
		this.server = new RtspServerStackImpl(this.bindAddress, this.rmPort);
		this.server.setRtspListener(this);
		this.server.start();
		logger.debug("Started RM of Video Server. Bound at IP " + this.bindAddress + " at port " + this.rmPort);

		//this.sm = new RtspServerStackImpl(this.bindAddress, this.smPort);
		//this.sm.setRtspListener(this);
		//this.sm.start();
		//logger.debug("Started SM of Video Server. Bound at IP " + this.bindAddress + " at port " + this.rmPort);
	}

	/**
	 * 停止.
	 */
	public void stop() {
		logger.debug("Stop Video Server. Listening at IP " + this.bindAddress);
		this.server.stop();
		//this.sm.stop();
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
	public void onRtspRequest(HttpRequest request, ChannelHandlerContext ctx) {
		logger.debug("Receive request " + request);
		Callable<HttpResponse> action = null;
		//Callable<HttpRequest> announceAction = null;
		HttpResponse response = null;
		//HttpRequest announce = null;

		try {
			if (request.getMethod().equals(RtspMethods.OPTIONS)) {
				action = new OptionsAction(request);
				response = action.call();
			} else if (request.getMethod().equals(RtspMethods.DESCRIBE)) {
				action = new DescribeAction(request);
				response = action.call();
			} else if (request.getMethod().equals(RtspMethods.SETUP)) {
				//InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel.getRemoteAddress();
				//String remoteIp = inetSocketAddress.getAddress().getHostAddress();
				String remoteIp = "192.168.14.116";
				action = new SetupAction(this, request, remoteIp);
				response = action.call();
			} else if (request.getMethod().equals(RtspMethods.PLAY)) {
				action = new PlayAction(this, request);
				response = action.call();
			} else if (request.getMethod().equals(RtspMethods.PAUSE)) {
				action = new PauseAction(this, request);
				response = action.call();
				//announceAction = new AnnounceAction(this, response);
				//announce = announceAction.call();

			} else if (request.getMethod().equals(RtspMethods.GET_PARAMETER)) {
				action = new GetParameterAction(this, request);
				response = action.call();
			} else if (request.getMethod().equals(RtspMethods.TEARDOWN)) {
				action = new TeardownAction(this, request);
				response = action.call();
			} else {
				response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.METHOD_NOT_ALLOWED);
				response.headers().set(HttpHeaders.Names.SERVER, RtspController.SERVER);
				response.headers().set(RtspHeaders.Names.CSEQ, request.headers().get(RtspHeaders.Names.CSEQ));
				response.headers().set(RtspHeaders.Names.ALLOW, "SETUP, PLAY");
			}
		} catch (Exception e) {
			logger.error("Unexpected error during processing,Caused by ", e);
			response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.INTERNAL_SERVER_ERROR);
			response.headers().add(HttpHeaders.Names.SERVER, RtspController.SERVER);
			response.headers().add(RtspHeaders.Names.CSEQ, request.headers().get(RtspHeaders.Names.CSEQ));
		}

		logger.debug("返回响应: \n" + response.toString());
		//ctx.write(response);
		//ctx.flush();
		
		ctx.channel().writeAndFlush(response);
		
		//if (null != announce) {
		//logger.debug("Sending Announce " + announce.toString());
		//channel.write(announce);
		//}
	}

	@Override
	public void onRtspResponse(HttpResponse response) {
	}

	/*-----------Setter And Getter --------------*/

	public String getBindAddress() {
		return bindAddress;
	}

	public void setBindAddress(String bindAddress) throws UnknownHostException {
		this.bindAddress = bindAddress;
	}

	public int getRmPort() {
		return rmPort;
	}

	public void setRmPort(int rmPort) {
		this.rmPort = rmPort;
	}

	public RtspSessionAccessor getSessionAccessor() {
		return sessionAccessor;
	}

	public void setSessionAccessor(RtspSessionAccessor sessionAccessor) {
		this.sessionAccessor = sessionAccessor;
	}

	public static RtspSessionKeyFactory getKeyFactory() {
		return keyFactory;
	}
}
