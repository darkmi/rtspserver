package com.darkmi.server.rtsp;

import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.netty.handler.codec.rtsp.RtspResponseStatuses;
import io.netty.handler.codec.rtsp.RtspVersions;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.darkmi.server.core.RtspController;
import com.darkmi.util.DateUtil;

/**
 * 
 * @author darkmi
 *
 */
public class SetupAction implements Callable<HttpResponse> {
	private static Logger logger = LoggerFactory.getLogger(SetupAction.class);
	private final RtspController rtspController;
	private final HttpRequest request;

	//private static final String REQUIRE_VALUE_HFC = "HFC.Delivery.Profile.1.0";
	//private static final String REQUIRE_VALUE_NGOD_R2 = "com.comcast.ngod.r2";

	public SetupAction(RtspController rtspController, HttpRequest request, String remoteIp) {
		this.rtspController = rtspController;
		this.request = request;
	}

	public HttpResponse call() throws Exception {
		FullHttpResponse response = null;

		//获取cesq
		String cseq = request.headers().get(RtspHeaders.Names.CSEQ);
		if (null == cseq || "".equals(cseq)) {
			logger.error("cesq is null.");
			response = new DefaultFullHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.INTERNAL_SERVER_ERROR);
			response.headers().set(HttpHeaders.Names.SERVER, RtspController.SERVER);
			response.headers().set(RtspHeaders.Names.CSEQ, request.headers().get(RtspHeaders.Names.CSEQ));
			return response;
		}
		//
		//		//根据require选择相应协议(HFC or NGOD)
		//		String requireVale = request.getHeader(RtspHeaders.Names.REQUIRE);
		//		if (null == requireVale || "".equals(requireVale)) {
		//			logger.error("require is null.");
		//			response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.INTERNAL_SERVER_ERROR);
		//			response.setHeader(HttpHeaders.Names.SERVER, RtspController.SERVER);
		//			response.setHeader(RtspHeaders.Names.CSEQ, this.request.getHeader(RtspHeaders.Names.CSEQ));
		//			return response;
		//		}
		//
		//获取Transport
		String strTransport = request.headers().get(RtspHeaders.Names.TRANSPORT);
		if (null == strTransport || strTransport.equals("")) {
			logger.error("strTransport is null.");
			response = new DefaultFullHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.INTERNAL_SERVER_ERROR);
			response.headers().set(HttpHeaders.Names.SERVER, RtspController.SERVER);
			response.headers().set(RtspHeaders.Names.CSEQ, this.request.headers().get(RtspHeaders.Names.CSEQ));
			return response;
		}
		//
		//		//		//获取SRM分配的资源
		//		//		RtspTransport rtspTransport = null;
		//		//		try {
		//		//			if (REQUIRE_VALUE_HFC.equalsIgnoreCase(requireVale)) {
		//		//				rtspTransport = new RtspTransport(strTransport, VideoTypeEnum.CCUR, TransportTypeEnum.REQUEST);
		//		//			} else if (REQUIRE_VALUE_NGOD_R2.equalsIgnoreCase(requireVale)) {
		//		//				rtspTransport = new RtspTransport(strTransport, VideoTypeEnum.DILU, TransportTypeEnum.REQUEST);
		//		//			}
		//		//		} catch (Exception e) {
		//		//			logger.error(e.toString());
		//		//		}
		//		//获取destination
		//		//String destination = rtspTransport.getDestination();
		//		String destination = null;
		//		if (null == destination || "".equals(destination)) {
		//			logger.error("destination is null.");
		//			response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.INTERNAL_SERVER_ERROR);
		//			response.setHeader(HttpHeaders.Names.SERVER, RtspController.SERVER);
		//			response.setHeader(RtspHeaders.Names.CSEQ, this.request.getHeader(RtspHeaders.Names.CSEQ));
		//			return response;
		//		}
		//		//获取port
		//		//int port = rtspTransport.getQam_port();
		//
		//		//创建session并记录使用资源
		//		@SuppressWarnings("unused")
		//		String sessionKey = RtspController.getKeyFactory().createSessionKey();
		//		logger.debug("sessionKey --> " + sessionKey);
		//		RtspSession rtspSession = rtspController.getSessionAccessor().getSession(sessionKey, true);
		//		rtspSession.setAttribute(destination + ":" + 1, "USED");
		//
		//		//----------------------
		//		if (REQUIRE_VALUE_HFC.equalsIgnoreCase(requireVale)) {
		//			logger.debug("vvs返回HFC协议响应。。。。。。。。。。。。");
		//
		//			response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.OK);
		//			response.setHeader(RtspHeaders.Names.CSEQ, cseq);
		//			response.setHeader(HttpHeaders.Names.DATE, DateUtil.getGmtDate());
		//			response.setHeader(RtspHeaders.Names.SESSION, sessionKey + ";timeout=60");
		//			response.setHeader(RtspHeaders.Names.TRANSPORT, "");
		//			response.setHeader(RtspHeaders.Names.RANGE, "npt=0-233.800");
		//			String location = "rtsp://" + rtspController.getBindAddress() + ":" + rtspController.getSmPort() + "/"
		//					+ request.getUri();
		//			response.setHeader(HttpHeaders.Names.LOCATION, location);
		//			return response;
		//		} else if (REQUIRE_VALUE_NGOD_R2.equalsIgnoreCase(requireVale)) {
		//			logger.debug("vvs返回NGOD协议响应。。。。。。。。。。。。");
		//			//构建返回给请求方的响应
		//
		
		response = new DefaultFullHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.OK);
		response.headers().set(RtspHeaders.Names.CSEQ, cseq);
		response.headers().set(HttpHeaders.Names.DATE, DateUtil.getGmtDate());
		response.headers().set(RtspHeaders.Names.SESSION, "aaa" + ";timeout=60");
		//response.setHeader(RtspHeaders.Names.OnDemandSessionId, request.getHeader(RtspHeaderCode.OnDemandSessionId));
		response.headers().set(RtspHeaders.Names.TRANSPORT, "");
		response.headers().set(RtspHeaders.Names.RANGE, "npt=0-233.800");
		response.headers().set(RtspHeaders.Names.CONTENT_TYPE, "application/sdp");

		//			//set sdp extension
		//			StringBuffer sdp = new StringBuffer();
		//			sdp.append("v=0\r\n");
		//			sdp.append("o=- " + sessionKey + " 1339005446 IN IP4 " + rtspController.getBindAddress() + "\r\n");
		//			sdp.append("s=RTSP Session\r\n");
		//			sdp.append("t=0 0\r\n");
		//			sdp.append("a=control:rtsp://" + rtspController.getBindAddress() + ":" + rtspController.getSmPort() + "/"
		//					+ sessionKey + "\r\n");
		//			sdp.append("c=IN IP4 0.0.0.0\r\n");
		//			sdp.append("m=video 0 RTP/AVP 33\r\n");
		//
		//			//设置SDP内容长度
		//			response.setHeader(RtspHeaders.Names.CONTENT_LENGTH, String.valueOf(sdp.length()));
		//			response.setContent(ChannelBuffers.copiedBuffer(sdp.toString(), "UTF-8"));
		//			//发送响应
		return response;

	}
}