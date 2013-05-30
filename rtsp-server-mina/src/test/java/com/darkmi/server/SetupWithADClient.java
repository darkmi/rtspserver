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

		StringBuffer sb = new StringBuffer();

		//SETUP
		sb.append("SETUP rtsp://192.168.14.14:554 RTSP/1.0\r\n");
		sb.append("CSeq: 1\r\n");
		sb.append("Require:com.comcase.ngod.r2\r\n");
		sb.append("OnDemandSessionId: df4bbd109aa832cc977596179bea3922\r\n");
		sb.append("Volume: central\r\n");
		//sb.append("Transport:MP2T/DVBC/UDP;unicast;destination=192.168.7.85;client_port=25001;\r\n");
		sb.append("Transport: MP2T/DVBC/UDP;Unicast;client=04-02-31-07-42-59;destination=10.0.6.129;client_port=49198;sop_name=central;bandwidth=3750000\r\n");
		sb.append("SessionGroup: central\r\n");
		sb.append("StreamControlProto: rtsp\r\n");
		sb.append("Policy: priority=1\r\n");
		sb.append("StartPoint: 1 0\r\n");
		sb.append("Content-type:application/sdp\r\n");
		sb.append("Content-length:316\r\n");
		sb.append("\r\n");
		sb.append("v=0\r\n");
		sb.append("o=- 0 0 IN IP4 192.168.14.14\r\n");
		sb.append("s=RTSP Session\r\n");
		sb.append("m=video 0 RTP/AVP 33\r\n");
		sb.append("a=X-playlist-item:gy001 SRTM4503309063941596 0-\r\n");
		//sb.append("a=X-playlist-item:dilifetech wwww0000000000000002 0-300.00\r\n");
		//sb.append("a=X-playlist-item:NovelSupertv BAAA0000000000018388 0-300.00\r\n");
		sb.append("\r\n");
		logger.debug(sb.toString());

		IoConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(30000);
		ProtocolCodecFilter myFilter = new ProtocolCodecFilter(new MessageCodecFactory());
		connector.getFilterChain().addLast("codec", myFilter);

		connector.setHandler(new ClientHandler(sb.toString()));

		connector.connect(new InetSocketAddress("192.168.14.14", 554));
	}
}
