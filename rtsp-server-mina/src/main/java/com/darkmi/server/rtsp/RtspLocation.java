package com.darkmi.server.rtsp;

import org.apache.commons.lang.StringUtils;

/**
 * Description:Location 
 * Copyright (c) 永新视博
 * All Rights Reserved.
 * @version 1.0  2012-02-01 下午01:55:38 Mxh created
 */
public class RtspLocation {

	private String protocol;
	private String host;
	private int port;
	private String uri;

	public RtspLocation(String url) {
		if (StringUtils.isNotBlank(url)) {
			String[] tok = url.split(":");
			protocol = tok[0];
			host = tok[1].substring(2);
			port = Integer.valueOf(tok[2].split("/")[0]);
			if (tok[2].split("/").length > 1)
				uri = tok[2].split("/")[1];
			else
				uri = StringUtils.EMPTY;
		}
	}

	public RtspLocation(String protocol, String host, int port, String uri) {
		super();
		this.protocol = protocol;
		this.host = host;
		this.port = port;
		this.uri = uri;
	}

	@Override
	public String toString() {

		return protocol + "://" + host + ":" + port + "/" + uri;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
