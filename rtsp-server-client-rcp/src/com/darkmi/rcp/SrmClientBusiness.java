package com.darkmi.rcp;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.handler.codec.http.DefaultHttpRequest;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.rtsp.RtspHeaders;
import org.jboss.netty.handler.codec.rtsp.RtspMethods;
import org.jboss.netty.handler.codec.rtsp.RtspVersions;

import com.darkmi.rtsp.RMClientStackImpl;
import com.darkmi.rtsp.RtspListener;
import com.darkmi.rtsp.RtspStack;
import com.darkmi.rtsp.SMClientStackImpl;


public class SrmClientBusiness implements RtspListener {
	RtspStack rmStack;
	private static final String RM_URI = "rtsp://192.168.14.62:554/";
	private static final String SM_URI = "rtsp://192.168.14.62:8060/";
	//private static String sessionId;
	private static String lastMethod;
	//private final Map<String, String> sessionMap = new ConcurrentHashMap<String, String>();
	private static Logger logger = Logger.getLogger(RtspBusiness.class);

	/**
	 * 连接554端口.
	 */
	private void connectRM() {
		logger.debug("连接到SRM的554端口 begin { ...");
		try {
			//连接到RM
			rmStack = new RMClientStackImpl("127.0.0.1", 554);
			rmStack.setRtspListener(this);
			Thread rmThread = new Thread(rmStack);
			rmThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("连接到SRM的554端口  end ...}");
	}

	/**
	 * 连接8060端口.
	 * @return
	 */
	private RtspStack connectSM() {
		logger.debug("连接到SRM的8060端口 begin { ...");
		RtspStack smStack = null;
		try {
			//连接到SM
			smStack = new SMClientStackImpl("127.0.0.1", 8060);
			smStack.setRtspListener(this);
			Thread smThread = new Thread(smStack);
			smThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("连接到SRM 的8060端口 end ...}");
		return smStack;
	}

	/**
	 * 发送SETUP请求.
	 */
	public void sendSetup(String smartCardId) {
		logger.debug("给SRM发送的554端口发送 SETUP begin {... ");

		//启动并连接SRM的554端口
		if (null == rmStack) {
			connectRM();
		}
		rmStack.start();

		HttpRequest vReq = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.SETUP, "rtsp://192.168.14.62:554/");
		vReq.setHeader(RtspHeaders.Names.CSEQ, "111");
		StringBuffer sb = new StringBuffer();
		sb.append("StreamType=Video;ChargeType=View;OfferingId=1355;ServiceId=tvsvod;MacAddress=001A34000001;SmartCardId=");
		sb.append(smartCardId);
		vReq.setHeader("UUData", sb.toString());
		vReq.setHeader(RtspHeaders.Names.TRANSPORT, "MP2T/DVBC/QAM;unicast;client=1");
		logger.debug("SETUP request --》" + vReq);
		rmStack.sendRquest(vReq);
		lastMethod = "SETUP";
		logger.debug("给SRM发送的554端口发送 SETUP end ...}");
	}

	/**
	 * 发送PLAY请求.
	 */
	public void sendPlay(String sessionId) {
		logger.debug("发送SRM的8060发送 PLAY begin { ... ");
		//创建SM客户端并连接SM
		RtspStack smClient = connectSM();
		smClient.start();
		HttpRequest vReq = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.PAUSE, SM_URI);
		vReq.setHeader(RtspHeaders.Names.CSEQ, "333");
		vReq.setHeader(RtspHeaders.Names.SCALE, 1);
		vReq.setHeader(RtspHeaders.Names.RANGE, "npt=55-299");
		vReq.setHeader(RtspHeaders.Names.SESSION, sessionId);
		logger.debug("PLAY request --》" + vReq);
		smClient.sendRquest(vReq);
		lastMethod = "PLAY";
		logger.debug("发送SRM的8060发送PLAY");
	}

	/**
	 * 发送teardown请求.
	 */
	public void sendTeardown(String smartCardId) {
		logger.debug("发送SRM的 554 端口发送 TEARDOWN begin { ... ");
		HttpRequest vReq = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.TEARDOWN, RM_URI);
		vReq.setHeader(RtspHeaders.Names.CSEQ, "666");
		//vReq.setHeader(RtspHeaders.Names.REQUIRE, "HFC.Delivery.Profile.1.0");
		//vReq.setHeader(RtspHeaders.Names.SESSION, sessionId);
		rmStack.sendRquest(vReq);
		lastMethod = "TEARDOWN";
		logger.debug("发送SRM的 554 端口发送 TEARDOWN end ... }");
	}

	/*~~~~~~~~~~~~ 抽象方法实现 ~~~~~~~~~~~~~~~~~~*/

	/**
	 * 接收到视频服务器返回请求时的处理.
	 * 客户端会接收到ANNOUNCE请求.
	 */
	@Override
	public void onRtspRequest(HttpRequest request, Channel chanel) {
		logger.debug("客户端接收到请求 --> " + request);
	}

	/**
	 * 接收到视频服务器返回响应时的处理.
	 * 
	 */
	@Override
	public void onRtspResponse(HttpResponse response) {
		logger.debug("接收到SRM的响应 begin{ ... ");
		HttpResponseStatus status = response.getStatus();
		logger.debug("status --> " + status);
		String transport = response.getHeader("Transport");
		logger.debug("transport --> " + transport);
		String sessionId = response.getHeader("Session");
		logger.debug("sessionId --> " + sessionId);

		if (lastMethod.equals("SETUP")) {
			if (HttpResponseStatus.OK.equals(status)) {
				logger.debug("SETUP成功,接收到SETUP响应.");
				//发送PLAY请求.
				sendPlay(sessionId);
			} else {
				logger.debug("SETUP失败.");

			}
		} else if (lastMethod.equals("PLAY")) {
			if (HttpResponseStatus.OK.equals(status)) {
				logger.debug("PLAY成功,接收到PLAY响应.");
			} else {
				logger.debug("PLAY失败.");

			}
		} else if (lastMethod.equals("TEARDOWN")) {
			if (HttpResponseStatus.OK.equals(status)) {
				logger.debug("TEARDOWN成功,接收到TEARDOWN响应.");
			} else {
				logger.debug("TEARDOWN失败.");

			}
		}

		//setResponse(response.toString());
		logger.debug("接收到SRM的响应 end ...}");
	}

	/*~~~~~~~~~~~~ Getter And Setter ~~~~~~~~~~~~~~~~~~*/

	//	public static String getSessionId() {
	//		return sessionId;
	//	}
	//
	//	public static String getSmUri() {
	//		return SM_URI;
	//	}

	//	/**
	//	 * 将响应显示到界面.
	//	 * @param response
	//	 */
	//	public void setResponse(final String response) {
	//		display.syncExec(new Runnable() {
	//			public void run() {
	//				gui.setResponse(response);
	//			}
	//		});
	//	}

	/*~~~~~~~~~~~~ 与界面交互的方法 ~~~~~~~~~~~~~~~~~~*/
	//	/**
	//	 * 获取主机配置信息.
	//	 */
	//	public void getSetting() {
	//		display.syncExec(new Runnable() {
	//			public void run() {
	//				host = gui.getHost();
	//				rmPort = gui.getRMPort();
	//				smPort = gui.getSMPort();
	//				providerId = gui.getProvider();
	//				contentId = gui.getContent();
	//				videoType = gui.getVideoServerType();
	//				protocol = gui.getProtocol();
	//			}
	//		});
	//	}
	//	/**
	//	 * 发送PLAY请求.
	//	 */
	//	private void sendHFCPlay() {
	//		logger.debug("发送PLAY....");
	//		HttpRequest vReq = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.PLAY, smUri);
	//		vReq.setHeader(RtspHeaders.Names.CSEQ, "111");
	//		vReq.setHeader(RtspHeaders.Names.REQUIRE, "HFC.Delivery.Profile.1.0");
	//		vReq.setHeader(RtspHeaders.Names.SESSION, sessionId);
	//		vReq.setHeader(RtspHeaders.Names.RANGE, "npt=0-");
	//		logger.debug(vReq);
	//		smStack.sendRquest(vReq);
	//	}
	//
	//	/**
	//	 * 发送PLAY请求.
	//	 */
	//	private void sendNGODPlay() {
	//		logger.debug("发送PLAY....");
	//		HttpRequest vReq = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.PLAY, smUri);
	//		vReq.setHeader(RtspHeaders.Names.CSEQ, "111");
	//		vReq.setHeader(RtspHeaders.Names.REQUIRE, REQUIRE_VALUE_NGOD_C1);
	//		vReq.setHeader(RtspHeaders.Names.SESSION, sessionId);
	//		vReq.setHeader(RtspHeaders.Names.RANGE, "npt=0-");
	//		logger.debug(vReq);
	//		smStack.sendRquest(vReq);
	//	}
	//	/**
	//	 * 发送PLAY请求.
	//	 */
	//	private void sendRiverPlay() {
	//		logger.debug("发送PLAY....");
	//		HttpRequest vReq = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.PLAY, smUri);
	//		vReq.setHeader(RtspHeaders.Names.CSEQ, "222");
	//		//vReq.setHeader(RtspHeaders.Names.REQUIRE, "HFC.Delivery.Profile.1.0");
	//		vReq.setHeader(RtspHeaders.Names.SESSION, sessionId);
	//		vReq.setHeader(RtspHeaders.Names.RANGE, "npt=0-");
	//		vReq.setHeader(RtspHeaders.Names.SCALE, "1");
	//		logger.debug(vReq);
	//		smStack.sendRquest(vReq);
	//	}
	//	/**
	//	 * 点击暂停.
	//	 */
	//	public void onPause() {
	//		logger.debug("发送PAUSE....");
	//		HttpRequest vReq = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.PAUSE, smUri);
	//		vReq.setHeader(RtspHeaders.Names.CSEQ, "333");
	//		//vReq.setHeader(RtspHeaders.Names.REQUIRE, REQUIRE_VALUE_NGOD_C1);
	//		vReq.setHeader(RtspHeaders.Names.SESSION, sessionId);
	//		logger.debug(vReq);
	//		smStack.sendRquest(vReq);
	//	}
	//
	//	/**
	//	 * 点击快进按钮.
	//	 */
	//	public void onFastForward() {
	//		logger.debug("发送快进请求....");
	//		HttpRequest vReq = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.PLAY, smUri);
	//		vReq.setHeader(RtspHeaders.Names.CSEQ, "444");
	//		//vReq.setHeader(RtspHeaders.Names.REQUIRE, "HFC.Delivery.Profile.1.0");
	//		vReq.setHeader(RtspHeaders.Names.SESSION, sessionId);
	//		vReq.setHeader(RtspHeaders.Names.RANGE, "npt=0-");
	//		vReq.setHeader(RtspHeaders.Names.SCALE, "10");
	//		logger.debug(vReq);
	//		smStack.sendRquest(vReq);
	//	}
	//	/**
	//	 * 点击快退按钮.
	//	 */
	//	public void onRewind() {
	//		logger.debug("发送快退请求....");
	//		HttpRequest vReq = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.PLAY, smUri);
	//		vReq.setHeader(RtspHeaders.Names.CSEQ, "555");
	//		//vReq.setHeader(RtspHeaders.Names.REQUIRE, "HFC.Delivery.Profile.1.0");
	//		vReq.setHeader(RtspHeaders.Names.SESSION, sessionId);
	//		vReq.setHeader(RtspHeaders.Names.RANGE, "npt=500");
	//		vReq.setHeader(RtspHeaders.Names.SCALE, "-10");
	//		logger.debug(vReq);
	//		smStack.sendRquest(vReq);
	//	}
	//	/**
	//	 * 点击连接按钮.<br>
	//	 * 连接到视频服务器.
	//	 */
	//	public void onConnect() {
	//		logger.debug("连接到SRM begin { ...");
	//		try {
	//			//连接到RM
	//			rmStack = new RMClientStackImpl("localhost", 554);
	//			rmStack.setRtspListener(this);
	//			Thread rmThread = new Thread(rmStack);
	//			rmThread.start();
	//
	//			//连接到SM
	//			smStack = new SMClientStackImpl("localhost", 8060);
	//			smStack.setRtspListener(this);
	//			Thread smThread = new Thread(smStack);
	//			smThread.start();
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//		logger.debug("连接到SRM end ...}");
	//	}
}