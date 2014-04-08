package com.darkmi.server.rtsp;

import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.netty.handler.codec.rtsp.RtspResponseStatuses;
import io.netty.handler.codec.rtsp.RtspVersions;
import io.netty.handler.codec.rtsp.RtspHeaders.Names;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.darkmi.server.core.RtspController;
import com.darkmi.server.session.RtspSession;
import com.darkmi.server.util.DateUtil;

public class GetParameterAction implements Callable<HttpResponse> {
  private static Logger logger = LoggerFactory.getLogger(GetParameterAction.class);
  private HttpRequest request = null;

  public GetParameterAction(HttpRequest request) {
    this.request = request;
  }

  public HttpResponse call() throws Exception {
    HttpResponse response = null;
    // get cesq
    String cseq = request.headers().get(Names.CSEQ);
    if (null == cseq || "".equals(cseq)) {
      logger.error("cesq is null.........");
      response =
          new DefaultFullHttpResponse(RtspVersions.RTSP_1_0,
              RtspResponseStatuses.INTERNAL_SERVER_ERROR);
      response.headers().set(Names.SERVER, RtspController.SERVER);
      response.headers().set("OnDemandSessionId", request.headers().get("OnDemandSessionId"));
      return response;
    }

    // get require
    String require = request.headers().get(Names.REQUIRE);
    if (null == require || "".equals(require)
        || (!require.equals(RtspController.REQUIRE_VALUE_NGOD_R2))) {
      logger.error("require is {}.........", require);
      response =
          new DefaultFullHttpResponse(RtspVersions.RTSP_1_0,
              RtspResponseStatuses.INTERNAL_SERVER_ERROR);
      response.headers().set(HttpHeaders.Names.SERVER, RtspController.SERVER);
      response.headers().set(RtspHeaders.Names.CSEQ, request.headers().get(RtspHeaders.Names.CSEQ));
      response.headers().set("OnDemandSessionId", request.headers().get("OnDemandSessionId"));
      return response;
    }

    String sessionKey = this.request.headers().get(Names.SESSION);
    if (null == sessionKey || "".equals(sessionKey)) {
      response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.BAD_REQUEST);
      response.headers().set(Names.SERVER, RtspController.SERVER);
      response.headers().set(Names.CSEQ, request.headers().get(Names.CSEQ));
      response.headers().set("OnDemandSessionId", request.headers().get("OnDemandSessionId"));
      return response;
    }

    // get session
    RtspSession rtspSession = RtspController.sessionAccessor.getSession(sessionKey, false);
    if (null == rtspSession) {
      logger.error("rtspSession is null.");
      response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.BAD_REQUEST);
      response.headers().set(Names.SERVER, RtspController.SERVER);
      response.headers().set(Names.CSEQ, request.headers().get(Names.CSEQ));
      response.headers().set("OnDemandSessionId", request.headers().get("OnDemandSessionId"));
      return response;
    }

    response = new DefaultHttpResponse(RtspVersions.RTSP_1_0, RtspResponseStatuses.OK);
    response.headers().set(Names.CSEQ, request.headers().get(Names.CSEQ));
    response.headers().set("OnDemandSessionId", request.headers().get("OnDemandSessionId"));
    response.headers().set(RtspHeaders.Names.DATE, DateUtil.getGmtDate());
    response.headers().set(RtspHeaders.Names.SESSION, sessionKey);
    response.headers().set(RtspHeaders.Names.RANGE, request.headers().get(RtspHeaders.Names.RANGE));

    String scale = request.headers().get(RtspHeaders.Names.SCALE);
    if (null != scale) {
      response.headers().set(RtspHeaders.Names.SCALE, scale);
    } else {
      response.headers().set(RtspHeaders.Names.SCALE, "1.00");
    }
    return response;
  }

}
