package com.darkmi.server.rtsp;

import io.netty.buffer.Unpooled;
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

public class SetupAction implements Callable<HttpResponse> {
	private static Logger logger = LoggerFactory.getLogger(SetupAction.class);
	private RtspController rtspController;
	private HttpRequest request;

	public SetupAction() {
	}

	public SetupAction(HttpRequest req) {
		this.request = req;
	}

	public SetupAction(RtspController rtspController, HttpRequest request) {
		this.rtspController = rtspController;
		this.request = request;
	}

	@Override
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

		//获取require
		String require = request.headers().get(RtspHeaders.Names.REQUIRE);
		if (null == require || "".equals(require)) {
			logger.error("require is null!!!!!!!!!!!!!!");
			response = new DefaultFullHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.INTERNAL_SERVER_ERROR);
			response.headers().set(HttpHeaders.Names.SERVER, RtspController.SERVER);
			response.headers().set(RtspHeaders.Names.CSEQ, this.request.headers().get(RtspHeaders.Names.CSEQ));
			return response;
		}

		//获取Transport
		String strTransport = request.headers().get(RtspHeaders.Names.TRANSPORT);
		if (null == strTransport || strTransport.equals("")) {
			logger.error("strTransport is null.");
			response = new DefaultFullHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.INTERNAL_SERVER_ERROR);
			response.headers().set(HttpHeaders.Names.SERVER, RtspController.SERVER);
			response.headers().set(RtspHeaders.Names.CSEQ, this.request.headers().get(RtspHeaders.Names.CSEQ));
			return response;
		}

		//获取SRM分配的资源
		//RtspTransport rtspTransport = null;
		//try {
		//	rtspTransport = new RtspTransport(strTransport);
		//} catch (Exception e) {
		//	logger.error(e.toString());
		//}
		//获取destination
		//String destination = rtspTransport.getDestination();
		//if (null == destination || "".equals(destination)) {
		//	logger.error("destination is null.");
		//	response = new DefaultFullHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.INTERNAL_SERVER_ERROR);
		//	response.headers().set(HttpHeaders.Names.SERVER, RtspController.SERVER);
		//	response.headers().set(RtspHeaders.Names.CSEQ, this.request.headers().get(RtspHeaders.Names.CSEQ));
		//	return response;
		//}

		//set sdp extension
		StringBuffer sdp = new StringBuffer();
		sdp.append("v=0\r\n");
		//sdp.append("o=- " + "" + " 1339005446 IN IP4 " + rtspController.getIp() + "\r\n");
		sdp.append("s=RTSP Session\r\n");
		sdp.append("t=0 0\r\n");
		//sdp.append("a=control:rtsp://" + rtspController.getIp() + ":" + rtspController.getPort() + "/" + "" + "\r\n");
		sdp.append("c=IN IP4 0.0.0.0\r\n");
		sdp.append("m=video 0 RTP/AVP 33\r\n");

		response = new DefaultFullHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.OK,
				Unpooled.wrappedBuffer(sdp.toString().getBytes()));
		//response = new DefaultFullHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.OK);
		response.headers().set(RtspHeaders.Names.CSEQ, cseq);
		response.headers().set(HttpHeaders.Names.DATE, DateUtil.getGmtDate());
		response.headers().set(RtspHeaders.Names.SESSION, "aaa" + ";timeout=60");
		response.headers().set("OnDemandSessionId", request.headers().get("OnDemandSessionId"));
		response.headers().set(RtspHeaders.Names.TRANSPORT, "");
		response.headers().set(RtspHeaders.Names.RANGE, "npt=0-233.800");
		response.headers().set(RtspHeaders.Names.CONTENT_TYPE, "application/sdp");

		//设置SDP内容长度
		response.headers().set(RtspHeaders.Names.CONTENT_LENGTH, String.valueOf(sdp.length()));
		//response.content().setBytes(0, Unpooled.wrappedBuffer(sdp.toString().getBytes()));
		//response.setContent(ChannelBuffers.copiedBuffer(sdp.toString(), "UTF-8"));
		//发送响应
		return response;

	}
}