package com.darkmi.rtsp;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.jboss.netty.handler.codec.http.DefaultHttpRequest;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.rtsp.RtspHeaders;
import org.jboss.netty.handler.codec.rtsp.RtspMethods;
import org.jboss.netty.handler.codec.rtsp.RtspVersions;

/**
 * 处理SETUP请求.
 * @author MiXiaohui
 *
 */
public class SetupAction implements Callable<HttpRequest> {
	private static Logger logger = Logger.getLogger(SetupAction.class);

	//private final RtspController rtspController;
	//private final HttpRequest request;
	//private static final String REQUIRE_VALUE_HFC = "HFC.Delivery.Profile.1.0";
	//private static final String REQUIRE_VALUE_NGOD_R2 = "com.comcast.ngod.r2";

	//	public SetupAction(RtspController rtspController, HttpRequest request, String remoteIp) {
	//		this.rtspController = rtspController;
	//		this.request = request;
	//	}

	public HttpRequest call() throws Exception {
		logger.debug("发送SETUP....");
		String setupUri = "rtsp://192.168.14.193:554/movie---98---2012";
		HttpRequest vReq = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.SETUP, setupUri);
		vReq.setHeader(RtspHeaders.Names.CSEQ, "111");
		vReq.setHeader(RtspHeaders.Names.REQUIRE, "HFC.Delivery.Profile.1.0");
		vReq.setHeader(RtspHeaders.Names.TRANSPORT, "MP2T/AVP;unicast;destination=10.1.1.196;port=49160");
		logger.debug(vReq);
		return vReq;
	}
}
