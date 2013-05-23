package cn.com.supertv.vvs;

import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.supertv.srmserver.rtsp.MessageCodecFactory;

public class GetParameterRequestAction {

	private static Logger logger = LoggerFactory.getLogger(GetParameterRequestAction.class);

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		//GET_PARAMETER
		sb.append("GET_PARAMETER rtsp://192.168.14.14:554/NovelSupertv:BAAA0000000000018389 RTSP/1.0\r\n");
		sb.append("CSeq: 4\r\n");
		sb.append("Session: 560071892\r\n");
		sb.append("Require:com.comcase.ngod.r2\r\n");
		sb.append("User-Agent: Dilife 20090303\r\n");
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
