package com.darkmi.rcp;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;
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


public class RtspBusiness implements RtspListener {
	private RtspGui gui;
	private Display display;
	RtspStack rmStack;
	RtspStack smStack;
	private String host;
	private String rmPort;
	private String smPort;
	private String providerId;
	private String contentId;
	private static String rmUri;
	private static String smUri;
	private static String sessionId;
	private static String lastMethod;
	private static String videoType;//视频服务器类型
	private static String protocol;//使用协议

	private static final String REQUIRE_VALUE_HFC = "HFC.Delivery.Profile.1.0";
	private static final String REQUIRE_VALUE_NGOD_R2 = "com.comcast.ngod.r2";
	private static final String REQUIRE_VALUE_NGOD_C1 = "com.comcast.ngod.c1";
	private static final String CCUR = "并行视频服务器";
	private static final String DILU = "迪麓视频服务器";
	private static final String RIVER = "川流视频服务器";
	//private static final String USER_AGENT = "SuperNovel 20120308";
	//private static final String USER_NOTIFIACATION_ALL = "all";

	private static Logger logger = Logger.getLogger(RtspBusiness.class);

	/**
	 * 构造函数.
	 * @param gui
	 */
	public RtspBusiness(RtspGui gui) {
		this.gui = gui;
		display = Display.getDefault();
	}

	/*~~~~~~~~~~~~ 按钮事件的处理 ~~~~~~~~~~~~~~~~~~*/

	/**
	 * 点击连接按钮.<br>
	 * 连接到视频服务器.
	 */
	public void onConnect() {
		getSetting();
		logger.debug("host --> " + host);
		logger.debug("rmPort --> " + rmPort);
		logger.debug("smPort --> " + smPort);
		logger.debug("providerId --> " + providerId);
		logger.debug("contentId --> " + contentId);
		logger.debug("videoServerType --> " + videoType);
		logger.debug("protocol --> " + protocol);
		rmUri = "rtsp://" + host + ":" + rmPort + "/" + providerId + ":" + contentId;
		smUri = "rtsp://" + host + ":" + smPort + "/" + providerId + ":" + contentId;
		logger.debug("rmUri --> " + rmUri);
		logger.debug("smUri --> " + smUri);
		try {
			//连接到RM
			rmStack = new RMClientStackImpl(host, Integer.parseInt(rmPort));
			rmStack.setRtspListener(this);
			Thread rmThread = new Thread(rmStack);
			rmThread.start();

			//连接到SM
			smStack = new SMClientStackImpl(host, Integer.parseInt(smPort));
			smStack.setRtspListener(this);
			Thread smThread = new Thread(smStack);
			smThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 点击播放按钮.<br>
	 * 点击播放按钮激活该方法.
	 */
	public void onPlay() {
		//首先发送SETUP请求,收到响应之后再发送PLAY请求
		if (videoType.equals(CCUR)) {
			sendHFCSetup();
		} else if (videoType.equals(DILU)) {
			sendNGODSetup();
		} else if (videoType.equals(RIVER)) {
			sendRiverSetup();
		} else {
			logger.error("没有匹配的视频服务器类型.");
		}
		lastMethod = "SETUP";
	}

	/**
	 * 发送SETUP请求.
	 */
	private void sendRiverSetup() {
		logger.debug("给川流视频服务器发送SETUP begin {... ");
		HttpRequest vReq = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.SETUP, rmUri);
		vReq.setHeader(RtspHeaders.Names.CSEQ, "111");
		vReq.setHeader(RtspHeaders.Names.TRANSPORT, "MP1S/AVP;destination=129.75.52.100;port=32794;unicast");
		logger.debug(vReq);
		rmStack.sendRquest(vReq);
		logger.debug("给川流视频服务器发送SETUP end ...}");
	}

	/**
	 * 发送SETUP请求For HFC.
	 */
	private void sendHFCSetup() {
		logger.debug("发送SETUP....");
		HttpRequest request = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.SETUP, rmUri);
		request.setHeader(RtspHeaders.Names.CSEQ, "111");
		request.setHeader(RtspHeaders.Names.REQUIRE, REQUIRE_VALUE_HFC);
		request.setHeader(RtspHeaders.Names.TRANSPORT, "MP2T/AVP;unicast;destination=10.1.1.196;port=49160");
		logger.debug(request);
		rmStack.sendRquest(request);
	}

	/**
	 * 发送SETUP请求 For NGOD.
	 */
	private void sendNGODSetup() {
		logger.debug("发送SETUP....");
		HttpRequest vReq = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.SETUP, rmUri);
		vReq.setHeader(RtspHeaders.Names.CSEQ, "111");
		vReq.setHeader(RtspHeaders.Names.REQUIRE, REQUIRE_VALUE_NGOD_R2);
		vReq.setHeader(RtspHeaders.Names.TRANSPORT, "MP2T/AVP;unicast;destination=10.1.1.196;port=49160");
		logger.debug(vReq);
		rmStack.sendRquest(vReq);
	}

	/**
	 * 点击暂停.
	 */
	public void onPause() {
		logger.debug("发送PAUSE....");
		HttpRequest vReq = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.PAUSE, smUri);
		vReq.setHeader(RtspHeaders.Names.CSEQ, "333");
		//vReq.setHeader(RtspHeaders.Names.REQUIRE, REQUIRE_VALUE_NGOD_C1);
		vReq.setHeader(RtspHeaders.Names.SESSION, sessionId);
		logger.debug(vReq);
		smStack.sendRquest(vReq);
	}

	/**
	 * 点击快进按钮.
	 */
	public void onFastForward() {
		logger.debug("发送快进请求....");
		HttpRequest vReq = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.PLAY, smUri);
		vReq.setHeader(RtspHeaders.Names.CSEQ, "444");
		//vReq.setHeader(RtspHeaders.Names.REQUIRE, "HFC.Delivery.Profile.1.0");
		vReq.setHeader(RtspHeaders.Names.SESSION, sessionId);
		vReq.setHeader(RtspHeaders.Names.RANGE, "npt=0-");
		vReq.setHeader(RtspHeaders.Names.SCALE, "10");
		logger.debug(vReq);
		smStack.sendRquest(vReq);
	}

	/**
	 * 点击快退按钮.
	 */
	public void onRewind() {
		logger.debug("发送快退请求....");
		HttpRequest vReq = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.PLAY, smUri);
		vReq.setHeader(RtspHeaders.Names.CSEQ, "555");
		//vReq.setHeader(RtspHeaders.Names.REQUIRE, "HFC.Delivery.Profile.1.0");
		vReq.setHeader(RtspHeaders.Names.SESSION, sessionId);
		vReq.setHeader(RtspHeaders.Names.RANGE, "npt=500");
		vReq.setHeader(RtspHeaders.Names.SCALE, "-10");
		logger.debug(vReq);
		smStack.sendRquest(vReq);
	}

	/**
	 * 发送teardown请求.
	 */
	public void onTeardown() {
		logger.debug("发送TEARDOWN....");
		HttpRequest vReq = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.TEARDOWN, rmUri);
		vReq.setHeader(RtspHeaders.Names.CSEQ, "666");
		//vReq.setHeader(RtspHeaders.Names.REQUIRE, "HFC.Delivery.Profile.1.0");
		vReq.setHeader(RtspHeaders.Names.SESSION, sessionId);
		rmStack.sendRquest(vReq);
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
		HttpResponseStatus status = response.getStatus();
		logger.debug("status --> " + status);
		String transport = response.getHeader("Transport");
		logger.debug("transport --> " + transport);
		
		//获取视频服务器返回的sessionId
		if (videoType.equals(CCUR)) {
			sessionId = response.getHeader("Session").split(";")[0];
		} else {
			sessionId = response.getHeader("Session");
		}
		logger.debug("sessionId --> " + sessionId);

		if (lastMethod.equals("SETUP")) {
			if (HttpResponseStatus.OK.equals(status)) {
				if (videoType.equals(CCUR)) {
					sendHFCPlay();
				} else if (videoType.equals(DILU)) {
					sendNGODPlay();
				} else if (videoType.equals(RIVER)) {
					sendRiverPlay();
				} else {
					logger.error("没有匹配的视频服务器类型.");
				}
			}
		}
		setResponse(response.toString());
	}

	/**
	 * 发送PLAY请求.
	 */
	private void sendHFCPlay() {
		logger.debug("发送PLAY....");
		HttpRequest vReq = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.PLAY, smUri);
		vReq.setHeader(RtspHeaders.Names.CSEQ, "111");
		vReq.setHeader(RtspHeaders.Names.REQUIRE, "HFC.Delivery.Profile.1.0");
		vReq.setHeader(RtspHeaders.Names.SESSION, sessionId);
		vReq.setHeader(RtspHeaders.Names.RANGE, "npt=0-");
		logger.debug(vReq);
		smStack.sendRquest(vReq);
	}

	/**
	 * 发送PLAY请求.
	 */
	private void sendNGODPlay() {
		logger.debug("发送PLAY....");
		HttpRequest vReq = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.PLAY, smUri);
		vReq.setHeader(RtspHeaders.Names.CSEQ, "111");
		vReq.setHeader(RtspHeaders.Names.REQUIRE, REQUIRE_VALUE_NGOD_C1);
		vReq.setHeader(RtspHeaders.Names.SESSION, sessionId);
		vReq.setHeader(RtspHeaders.Names.RANGE, "npt=0-");
		logger.debug(vReq);
		smStack.sendRquest(vReq);
	}

	/**
	 * 发送PLAY请求.
	 */
	private void sendRiverPlay() {
		logger.debug("发送PLAY....");
		HttpRequest vReq = new DefaultHttpRequest(RtspVersions.RTSP_1_0, RtspMethods.PLAY, smUri);
		vReq.setHeader(RtspHeaders.Names.CSEQ, "222");
		//vReq.setHeader(RtspHeaders.Names.REQUIRE, "HFC.Delivery.Profile.1.0");
		vReq.setHeader(RtspHeaders.Names.SESSION, sessionId);
		vReq.setHeader(RtspHeaders.Names.RANGE, "npt=0-");
		vReq.setHeader(RtspHeaders.Names.SCALE, "1");
		logger.debug(vReq);
		smStack.sendRquest(vReq);
	}

	//	@Override
	//	public void onRtspResponse(String response) {
	//		setResponse(response.toString());
	//	}

	/*~~~~~~~~~~~~ Getter And Setter ~~~~~~~~~~~~~~~~~~*/

	public static String getSessionId() {
		return sessionId;
	}

	public static String getSmUri() {
		return smUri;
	}

	/*~~~~~~~~~~~~ 与界面交互的方法 ~~~~~~~~~~~~~~~~~~*/
	/**
	 * 获取主机配置信息.
	 */
	public void getSetting() {
		display.syncExec(new Runnable() {
			public void run() {
				host = gui.getHost();
				rmPort = gui.getRMPort();
				smPort = gui.getSMPort();
				providerId = gui.getProvider();
				contentId = gui.getContent();
				videoType = gui.getVideoServerType();
				protocol = gui.getProtocol();
			}
		});
	}

	/**
	 * 将响应显示到界面.
	 * @param response
	 */
	public void setResponse(final String response) {
		display.syncExec(new Runnable() {
			public void run() {
				gui.setResponse(response);
			}
		});
	}

}