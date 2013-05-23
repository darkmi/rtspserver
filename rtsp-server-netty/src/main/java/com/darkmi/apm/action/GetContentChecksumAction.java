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


import com.darkmi.apm.request.GetContentChecksumReq;
import com.thoughtworks.xstream.XStream;

/**
 * 处理GetTransferStatus请求.
 * @author MiXiaohui
 *
 */
public class GetContentChecksumAction implements Callable<HttpResponse> {
	private static final Logger logger = Logger.getLogger(GetContentChecksumAction.class);
	private HttpRequest request = null;

	/**
	 * 构造函数.
	 * @param request
	 */
	public GetContentChecksumAction(HttpRequest request) {
		this.request = request;
	}

	/**
	 * 业务处理并返回响应.
	 */
	public HttpResponse call() throws Exception {
		String xml = getXML(request);
		logger.debug("xml \n" + xml);
		GetContentChecksumReq req = xmlToTc(xml);
		logger.debug("GetContentChecksumReq \n" + req);
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
	 * 将XML转换为java对象.
	 * @param xml
	 * @return
	 */
	private GetContentChecksumReq xmlToTc(String xml) {
		XStream xstream = new XStream();
		xstream.alias("GetContentChecksum", GetContentChecksumReq.class);
		xstream.useAttributeFor(GetContentChecksumReq.class, "providerID");
		xstream.useAttributeFor(GetContentChecksumReq.class, "assetID");
		xstream.useAttributeFor(GetContentChecksumReq.class, "volumeName");
		xstream.useAttributeFor(GetContentChecksumReq.class, "responseURL");
		GetContentChecksumReq req = (GetContentChecksumReq) xstream.fromXML(xml);
		return req;
	}

	/**
	 * 把xml写入本地文件.
	 */
	private void writeXmlToFile(String xml) {
		try {
			FileUtils.writeStringToFile(new File("./xml/GetContentChecksum.xml"), xml, "UTF-8");
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
	}

	/**
	 * 构造响应.
	 * @return
	 */
	private HttpResponse createResponse() {
		HttpResponse response = new DefaultHttpResponse(HTTP_1_1, HttpResponseStatus.OK);
		response.setHeader(RtspHeaders.Names.CSEQ, request.getHeader(RtspHeaders.Names.CSEQ));
		return response;
	}
}
