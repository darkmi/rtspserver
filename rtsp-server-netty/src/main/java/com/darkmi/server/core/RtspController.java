package com.darkmi.server.core;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.netty.handler.codec.rtsp.RtspMethods;
import io.netty.handler.codec.rtsp.RtspResponseStatuses;
import io.netty.handler.codec.rtsp.RtspVersions;

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
	private static final RtspSessionKeyFactory keyFactory = new SimpleRandomKeyFactory();
	public static final String SERVER = "RtspServer";
	private String ip;
	private int port;
	private RtspStack server = null;
	private RtspStack client = null;
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
		//start server.
		this.server = new RtspServerStackImpl(this.ip, this.port);
		this.server.setRtspListener(this);
		this.server.start();
		logger.debug("Started Rtsp Server. Bound at IP " + this.ip + " at port " + this.port);

		//start client.
		client = new RtspClientStackImpl();
		client.start();
		logger.debug("Started Rtsp Client. ");
	}

	/**
	 * 停止.
	 */
	public void stop() {
		logger.debug("Stop Video Server. Listening at IP " + this.ip);
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
		logger.debug("Receive request ==> \n " + request);
		Callable<HttpResponse> action = null;
		HttpResponse response = null;
		//Callable<HttpRequest> announceAction = null;
		//HttpRequest announce = null;

		try {
			if (request.getMethod().equals(RtspMethods.OPTIONS)) {
				action = new OptionsAction(request);
				response = action.call();
			} else if (request.getMethod().equals(RtspMethods.DESCRIBE)) {
				action = new DescribeAction(request);
				response = action.call();
			} else if (request.getMethod().equals(RtspMethods.SETUP)) {
				response = onSetupRequest(request);
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

	private HttpResponse onSetupRequest(HttpRequest request) {
		try {
			//处理请求
			// Prepare the RTSP request.
			StringBuffer url = new StringBuffer();
			url.append("rtsp://").append("192.168.80.50").append(":").append(554);

			StringBuffer sdp = new StringBuffer();
			sdp.append("v=0");
			sdp.append("o=- 22b03f0e-170b-4845-8c4a-1d7bac576bbc  IN IP4 0.0.0.0");
			sdp.append("s=");
			sdp.append("c=IN IP4 0.0.0.0t=0 0");
			sdp.append("a=X-playlist-item: 10002 movi2010000004329519 0-");
			sdp.append("m=video 0 udp MP2T");

			FullHttpRequest req = new DefaultFullHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.SETUP, url.toString(),
					Unpooled.wrappedBuffer(sdp.toString().getBytes()));
			req.headers().set(RtspHeaders.Names.CSEQ, "10");

			req.headers().set(RtspHeaders.Names.CONTENT_TYPE, "application/sdp");
			req.headers().set("OnDemandSessionId", "22b03f0e-170b-4845-8c4a-1d7bac576bbc");
			req.headers().set(RtspHeaders.Names.REQUIRE, "com.comcast.ngod.r2");
			req.headers().set("SessionGroup", "80333.20");
			req.headers().set("StreamControlProto", "rtsp");

			req.headers()
					.set(RtspHeaders.Names.TRANSPORT,
							"MP2T/DVBC/UDP;unicast;destination=192.168.88.1;client_port=782;bandwidth=10005600;client=;sop_group=CDN-OSTR1-F;sop_name=CDN-OSTR1-F");

			req.headers().set(RtspHeaders.Names.USER_AGENT, "NSS/1.16");
			req.headers().set("Volume", "/files");
			req.headers().set("Content-Length", sdp.length());
			req.headers().set("Date", "Mon, 24 Feb 2014 03:21:44 GMT");

			logger.debug("video server setup =====> \n" + req);

			client.sendRequest(req, "192.168.80.50", 554);

			//构造响应
			Callable<HttpResponse> action = new SetupAction(this, request);
			return action.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void onRtspResponse(HttpResponse response) {
		logger.debug(" video server response  ==>\n  " + response);

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
