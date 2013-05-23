package com.darkmi.rtsp;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.http.DefaultHttpRequest;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.rtsp.RtspHeaders;
import org.jboss.netty.handler.codec.rtsp.RtspMethods;
import org.jboss.netty.handler.codec.rtsp.RtspVersions;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;

import com.darkmi.rcp.RtspBusiness;


/**
 * 心跳处理器.
 * @author MiXiaohui
 *
 */
public class HeartbeatHandler extends IdleStateAwareChannelHandler {
	private static Logger logger = Logger.getLogger(HeartbeatHandler.class);

	@Override
	public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e) {
		logger.debug("双端空闲.............");
		//String gpUri = "rtsp://192.168.14.193:8060/movie---98---2012";
		HttpRequest vReq = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.GET_PARAMETER,
				RtspBusiness.getSmUri());
		vReq.setHeader(RtspHeaders.Names.CSEQ, "333");
		vReq.setHeader(RtspHeaders.Names.DATE, "Date: 26 Apr 2011 18:04:07 GMT");
		//vReq.setHeader(RtspHeaders.Names.REQUIRE, "HFC.Delivery.Profile.1.0");
		vReq.setHeader(RtspHeaders.Names.SESSION, RtspBusiness.getSessionId());
		logger.debug(vReq);
		e.getChannel().write(vReq);

		//		if (e.getState() == IdleState.READER_IDLE) {
		//			//logger.debug("读空闲.............");
		//		} else if (e.getState() == IdleState.WRITER_IDLE) {
		//			//logger.debug("写空闲.............");
		//		} else if (e.getState() == IdleState.ALL_IDLE) {
		//		}
	}

	@Override
	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
		logger.debug("HeartbeatHandler --> handleUpstream");
		super.handleUpstream(ctx, e);
	}
}
