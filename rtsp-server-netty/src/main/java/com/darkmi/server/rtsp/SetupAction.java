package com.darkmi.server.rtsp;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.netty.handler.codec.rtsp.RtspHeaders.Names;
import io.netty.handler.codec.rtsp.RtspResponseStatuses;
import io.netty.handler.codec.rtsp.RtspVersions;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.darkmi.server.config.ServerConfig;
import com.darkmi.server.core.RtspController;
import com.darkmi.server.session.RtspSession;
import com.darkmi.server.util.DateUtil;

public class SetupAction implements Callable<FullHttpResponse> {
	private static Logger logger = LoggerFactory.getLogger(SetupAction.class);
	private HttpRequest request;
	private ServerConfig config;

	public SetupAction(ServerConfig config, HttpRequest request) {
		this.config = config;
		this.request = request;
	}

	@Override
	public FullHttpResponse call() throws Exception {
		FullHttpResponse response = null;

		//get cesq
		String cseq = request.headers().get(Names.CSEQ);
		if (null == cseq || "".equals(cseq)) {
			logger.error("cesq is null.........");
			response = new DefaultFullHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.INTERNAL_SERVER_ERROR);
			response.headers().set(Names.SERVER, RtspController.SERVER);
			response.headers().set("OnDemandSessionId", request.headers().get("OnDemandSessionId"));
			return response;
		}

		//get require
		String require = request.headers().get(Names.REQUIRE);
		if (null == require || "".equals(require) || (!require.equals(RtspController.REQUIRE_VALUE_NGOD_R2))) {
			logger.error("require is {}.........", require);
			response = new DefaultFullHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.INTERNAL_SERVER_ERROR);
			response.headers().set(HttpHeaders.Names.SERVER, RtspController.SERVER);
			response.headers().set(RtspHeaders.Names.CSEQ, request.headers().get(RtspHeaders.Names.CSEQ));
			response.headers().set("OnDemandSessionId", request.headers().get("OnDemandSessionId"));
			return response;
		}

		//get Transport
		String transport = request.headers().get(Names.TRANSPORT);
		if (null == transport || transport.equals("")) {
			logger.error("transport is null.........");
			response = new DefaultFullHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.INTERNAL_SERVER_ERROR);
			response.headers().set(HttpHeaders.Names.SERVER, RtspController.SERVER);
			response.headers().set(RtspHeaders.Names.CSEQ, this.request.headers().get(RtspHeaders.Names.CSEQ));
			return response;
		}

		String sessionKey = RtspController.keyFactory.createSessionKey();
		logger.debug("sessionKey --> " + sessionKey);
		RtspSession rtspSession = RtspController.sessionAccessor.getSession(sessionKey, true);
		//save transport
		rtspSession.setAttribute(transport, "USED");

		//set sdp extension
		StringBuffer sdp = new StringBuffer();
		sdp.append("v=0\r\n");
		sdp.append("o=- " + "" + " " + sessionKey + " IN IP4 " + config.getIp() + "\r\n");
		sdp.append("s=RTSP Session\r\n");
		sdp.append("t=0 0\r\n");
		sdp.append("a=control:rtsp://" + config.getIp() + ":" + config.getPort() + "/" + sessionKey + "\r\n");
		sdp.append("c=IN IP4 0.0.0.0\r\n");
		sdp.append("m=video 0 RTP/AVP 33\r\n");

		response = new DefaultFullHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.OK,
				Unpooled.wrappedBuffer(sdp.toString().getBytes()));
		response.headers().set(Names.CSEQ, cseq);
		response.headers().set(Names.DATE, DateUtil.getGmtDate());
		response.headers().set(Names.SESSION, sessionKey + ";timeout=60");
		response.headers().set("OnDemandSessionId", request.headers().get("OnDemandSessionId"));
		response.headers().set(Names.TRANSPORT, "");
		response.headers().set(Names.RANGE, "npt=0-233.800");
		response.headers().set(Names.CONTENT_TYPE, "application/sdp");
		response.headers().set(Names.CONTENT_LENGTH, String.valueOf(sdp.length()));
		return response;
	}
}