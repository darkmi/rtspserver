package com.darkmi.apm.action;

import static org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.io.File;
import java.util.concurrent.Callable;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.rtsp.RtspHeaders;


import com.darkmi.apm.request.ExposeContentReq;
import com.thoughtworks.xstream.XStream;

/**
 * 处理CancelTransfer请求.
 * @author MiXiaohui
 *
 */
public class ExposeContentAction implements Callable<HttpResponse> {
	private static final Logger logger = Logger.getLogger(TransferContentAction.class);
	private HttpRequest request = null;

	/**
	 * 构造函数.
	 * @param request
	 */
	public ExposeContentAction(HttpRequest request) {
		this.request = request;
	}

	/**
	 * 业务处理并返回响应.
	 */
	public HttpResponse call() throws Exception {
		String xml = getXML(request);
		logger.debug("xml \n" + xml);
		ExposeContentReq req = xmlToReq(xml);
		logger.debug("ExposeContentReq \n" + req);
		writeXmlToFile(xml);
		HttpResponse response = createResponse();
		logger.debug("TransferContent Response \n" + response);
		return response;
	}

	/**
	 * 获取请求body并转换为xml文件.
	 * @param request
	 * @return
	 */
	private String getXML(HttpRequest request) {
		//接收并转换xml指令
		ChannelBuffer cb = request.getContent();
		byte[] b = new byte[cb.capacity()];
		cb.getBytes(0, b);
		String xml = new String(b);
		return xml;
	}

	/**
	 * 将xml文件流转换为java对象.
	 * @param xml
	 * @return
	 */
	private ExposeContentReq xmlToReq(String xml) {
		XStream xstream = new XStream();
		xstream.alias("ExposeContent", ExposeContentReq.class);
		xstream.useAttributeFor(ExposeContentReq.class, "providerID");
		xstream.useAttributeFor(ExposeContentReq.class, "assetID");
		xstream.useAttributeFor(ExposeContentReq.class, "volumeName");
		xstream.useAttributeFor(ExposeContentReq.class, "protocol");
		xstream.useAttributeFor(ExposeContentReq.class, "transferBitRate");
		ExposeContentReq req = (ExposeContentReq) xstream.fromXML(xml);
		return req;
	}

	/**
	 * 把xml写入本地文件.
	 */
	private void writeXmlToFile(String xml) {
		try {
			FileUtils.writeStringToFile(new File("./xml/ExposeContent.xml"), xml, "UTF-8");
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
	}

	/**
	 * 创建响应.
	 * @return
	 */
	private HttpResponse createResponse() {
		HttpResponse response = new DefaultHttpResponse(HTTP_1_1, HttpResponseStatus.OK);
		response.setHeader(RtspHeaders.Names.CSEQ, request.getHeader(RtspHeaders.Names.CSEQ));
		return response;
	}
}