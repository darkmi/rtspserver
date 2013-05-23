package com.darkmi.phoneremote.protocol;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import com.darkmi.phoneremote.protocol.receive.ResponseDecoder;
import com.darkmi.phoneremote.protocol.send.ResponseEncoder;



/**
 * Description: 信息编解码工厂
 * Copyright (c) 永新视博
 * All Rights Reserved.
 * @version 1.0  2011-4-17 上午10:28:36 laojiang created
 */
public class MessageCodecFactory implements ProtocolCodecFactory {

	/**
	 * 获取解码器
	 */
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return new ResponseDecoder();
	}

	/**
	 * 获取编码器
	 */
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return new ResponseEncoder();
	}

}
