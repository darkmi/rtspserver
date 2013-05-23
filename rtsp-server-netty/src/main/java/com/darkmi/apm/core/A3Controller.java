package com.darkmi.apm.core;

import static org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.net.UnknownHostException;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.rtsp.RtspHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import com.darkmi.apm.action.CancelTransferAction;
import com.darkmi.apm.action.DeleteContentAction;
import com.darkmi.apm.action.ExposeContentAction;
import com.darkmi.apm.action.GetContentChecksumAction;
import com.darkmi.apm.action.GetContentInfoAction;
import com.darkmi.apm.action.GetTransferStatusAction;
import com.darkmi.apm.action.GetVolumeInfoAction;
import com.darkmi.apm.action.TransferContentAction;
import com.darkmi.apm.request.URIEnum;


/**
 * 视频服务器启动类.
 * @author MiXiaohui.
 *
 */
public class A3Controller implements A3Listener {
	private static final Logger logger = Logger.getLogger(A3Controller.class);
	private String bindAddress;
	private int port;
	private A3ServerStackImpl apm = null;
	public static final String SERVER = "APM";

	/**
	 * 创建.
	 */
	public void create() {
		logger.info("Starting RTSP Controller module for VideoServer");
	}

	/**
	 * 启动.
	 * @throws Exception
	 */
	public void start() throws Exception {
		this.apm = new A3ServerStackImpl(this.bindAddress, this.port);
		this.apm.setA3Listener(this);
		this.apm.start();
		logger.info("Started APM Module of Video Server. Bound at IP " + this.bindAddress + " at port " + this.port);
	}

	/**
	 * 停止.
	 */
	public void stop() {
		logger.info("Stop APM Module of Video Server. Listening at IP " + this.bindAddress);
		this.apm.stop();
	}

	/**
	 * 销毁.
	 */
	public void destroy() {
		logger.info("Stopped APM Module of Video Server.");
	}

	/**
	 * 请求分发.
	 */
	@Override
	public void onA3Request(HttpRequest request, Channel channel) {
		logger.debug("Receive request " + request);
		Callable<HttpResponse> action = null;
		HttpResponse response = null;
		String uri = request.getUri();
		if (uri == null) {
			logger.error("uri is null.");
		} else {
			uri = uri.replace("/", "");
		}
		logger.debug("uri -->" + uri);
		try {
			if (uri.equals(URIEnum.TransferContent.toString())) {
				logger.debug("URIEnum.TransferContent...");
				action = new TransferContentAction(request);
				response = action.call();
			} else if (uri.equals(URIEnum.GetTransferStatus.toString())) {
				logger.debug("URIEnum.GetTransferStatus...");
				action = new GetTransferStatusAction(request);
				response = action.call();
			} else if (uri.equals(URIEnum.CancelTransfer.toString())) {
				logger.debug("URIEnum.CancelTransfer...");
				action = new CancelTransferAction(request);
				response = action.call();
			} else if (uri.equals(URIEnum.GetContentInfo.toString())) {
				logger.debug("URIEnum.GetContentInfo...");
				action = new GetContentInfoAction(request);
				response = action.call();
			} else if (uri.equals(URIEnum.DeleteContent.toString())) {
				logger.debug("URIEnum.DeleteContent...");
				action = new DeleteContentAction(request);
				response = action.call();
			} else if (uri.equals(URIEnum.ExposeContent.toString())) {
				logger.debug("URIEnum.ExposeContent...");
				action = new ExposeContentAction(request);
				response = action.call();
			} else if (uri.equals(URIEnum.GetContentChecksum.toString())) {
				logger.debug("URIEnum.GetContentChecksum...");
				action = new GetContentChecksumAction(request);
				response = action.call();
			} else if (uri.equals(URIEnum.GetVolumeInfo.toString())) {
				logger.debug("URIEnum.GetVolumeInfo...");
				action = new GetVolumeInfoAction(request);
				response = action.call();
			} else {
				response = new DefaultHttpResponse(HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
				response.setHeader(RtspHeaders.Names.CSEQ, request.getHeader(RtspHeaders.Names.CSEQ));
			}
		} catch (Exception e) {
			logger.error("Unexpected error during processing,Caused by ", e);
			response = new DefaultHttpResponse(HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
			response.setHeader(RtspHeaders.Names.CSEQ, request.getHeader(RtspHeaders.Names.CSEQ));
		}
		//logger.info("Sending Response " + response.toString() + " For Request " + request.toString());
		channel.write(response);
	}

	@Override
	public void onA3Response(HttpResponse response) {
	}

	/*-----------Setter And Getter --------------*/

	public int getPort() {
		return port;
	}

	public String getBindAddress() {
		return bindAddress;
	}

	/*----------- 注入 --------------*/

	@Autowired
	public void setBindAddress(String bindAddress) throws UnknownHostException {
		this.bindAddress = bindAddress;
	}

	@Autowired
	public void setPort(int port) {
		this.port = port;
	}
}