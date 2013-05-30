package com.darkmi.server.rtsp;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class MessageCodecFactory implements ProtocolCodecFactory {

	/* 
	 * 获取解码器
	 */
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return new RtspDecoder();
	}

	/* 
	 * 获取编码器
	 */
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return new RtspEncoder();
	}

}
