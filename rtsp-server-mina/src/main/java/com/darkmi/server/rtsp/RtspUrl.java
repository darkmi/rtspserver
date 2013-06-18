package com.darkmi.server.rtsp;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RtspUrl {
	private static Logger logger = LoggerFactory.getLogger(RtspUrl.class);
	private String protocol;
	private String host;
	private Integer port;
	private String uri;

	public RtspUrl() {
	}

	public RtspUrl(String url) {
		if (StringUtils.isNotBlank(url)) {
			String[] tok = url.split(":");
			protocol = tok[0];
			String urlTail = tok[1].substring(2);
			logger.debug("urlTail --> " + urlTail);

			if ((!urlTail.contains(":")) && (!urlTail.contains("/"))) {
				host = urlTail;
			} else {

				String[] hostAndPort = urlTail.split("/");

				if (hostAndPort.length < 2) {
					host = hostAndPort[0];
				} else {

				}

				logger.debug("hostAndPort.length --> {}", hostAndPort.length);

				host = urlTail.split("/")[0];
				logger.debug("host --> " + host);
				if (urlTail.contains(":")) {
					host = urlTail.split(":")[0];
					String hostTail = urlTail.split(":")[1];
					if (hostTail.contains("/")) {
						port = Integer.parseInt(hostTail.split("/")[0]);
						uri = hostTail.split("/")[1];
					}
				} else {
					host = urlTail.split("/")[0];
					uri = urlTail.split("/")[1];
				}
			}
		}
	}

	public RtspUrl(String protocol, String host, int port, String uri) {
		super();
		this.protocol = protocol;
		this.host = host;
		this.port = port;
		this.uri = uri;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		if (null != protocol) {
			sb.append(protocol);
		}
		if (null != host) {
			sb.append("://").append(host);
		}
		if (null != port) {
			sb.append(":").append(port);
		}
		if (null != uri) {
			sb.append("/").append(uri);
		}
		return sb.toString();
		//return protocol + "://" + host + ":" + port + "/" + uri;
	}

	/*~~~~~~~~ Setter And Getter ~~~~~~~~~~*/

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

	public static void main(String[] args) {
		String strUrl1 = "rtsp://172.168.2.2";
		String strUrl2 = "rtsp://172.168.2.2/";
		String strUrl3 = "rtsp://172.168.2.2:555";
		String strUrl4 = "rtsp://172.168.2.2:555/";
		String strUrl5 = "rtsp://172.168.2.2/Comcast.Philly.NE.1234";
		String strUrl6 = "rtsp://172.168.2.2:555/Comcast.Philly.NE.1234";

		RtspUrl url = new RtspUrl(strUrl1);
		System.out.println(url);

		RtspUrl url2 = new RtspUrl(strUrl2);
		System.out.println(url2);

		RtspUrl url3 = new RtspUrl(strUrl3);
		System.out.println(url3);

		RtspUrl url4 = new RtspUrl(strUrl4);
		System.out.println(url4);

		RtspUrl url5 = new RtspUrl(strUrl5);
		System.out.println(url5);

		RtspUrl url6 = new RtspUrl(strUrl6);
		System.out.println(url6);

	}
}
