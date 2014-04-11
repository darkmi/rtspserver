package com.darkmi.server;

import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.darkmi.server.rtsp.MessageCodecFactory;

public class SetupWithADClient {
  private static Logger logger = LoggerFactory.getLogger(SetupWithADClient.class);

  public static void main(String[] args) {

    StringBuffer sdp = new StringBuffer();
    sdp.append("v=0\r\n");
    sdp.append("o=- 22b03f0e-170b-4845-8c4a-1d7bac576bbc  IN IP4 0.0.0.0\r\n");
    sdp.append("s=\r\n");
    sdp.append("c=IN IP4 0.0.0.0\r\n");
    sdp.append("t=0 0\r\n");
    sdp.append("a=X-playlist-item: 10002 movi2010000004329519 0-\r\n");
    sdp.append("m=video 0 udp MP2T\r\n");

    StringBuffer sb = new StringBuffer();

    // SETUP
    sb.append("SETUP rtsp://192.168.14.116 RTSP/1.0\r\n");
    sb.append("CSeq: 1\r\n");
    sb.append("Content-Type: application/sdp\r\n");
    sb.append("OnDemandSessionId: 22b03f0e-170b-4845-8c4a-1d7bac576bbc\r\n");
    sb.append("Require: com.comcast.ngod.r2\r\n");
    sb.append("SessionGroup: 80333.20\r\n");
    sb.append("StreamControlProto: rtsp\r\n");
    sb.append("Transport:  MP2T/DVBC/UDP;unicast;destination=192.168.88.1;client_port=782;bandwidth=10005600;client=;sop_group=CDN-OSTR1-F;sop_name=CDN-OSTR1-F\r\n");
    sb.append("User-Agent: NSS/1.16\r\n");
    sb.append("Volume: /files\r\n");
    sb.append("Content-Length: " + sdp.length() + "\r\n");
    sb.append("Date: Mon, 24 Feb 2014 03:21:44 GMT\r\n");
    sb.append("\r\n");

    sb.append(sdp.toString());

    logger.debug("setup request ==> \n{}", sb.toString());

    IoConnector connector = new NioSocketConnector();
    connector.setConnectTimeoutMillis(30000);
    ProtocolCodecFilter myFilter = new ProtocolCodecFilter(new MessageCodecFactory());
    connector.getFilterChain().addLast("codec", myFilter);

    connector.setHandler(new ClientHandler(sb.toString()));

    connector.connect(new InetSocketAddress("192.168.14.116", 554));
  }
}
