package com.darkmi.phoneremote.protocol.send;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * Description:  响应报文编码器
 * Copyright (c) 永新视博
 * All Rights Reserved.
 * @version 1.0  2011-4-17 上午10:52:39 laojiang created
 */
public class ResponseEncoder extends ProtocolEncoderAdapter {

	/* 
	 * 
	 * 编码操作，将Response对象转为二进制数据
	 */
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
//		Response response = (Response) message;
//		byte[] bs = response.getBytes();
//		logger.debug("IVR返回的数据:{}", StringUtil.toHexString(bs));
//		IoBuffer buffer = IoBuffer.allocate(bs.length, false);
//		buffer.put(bs);
//		buffer.flip();
//		out.write(buffer);
		byte[] bb = (byte[])message;
		IoBuffer buffer = IoBuffer.wrap((bb));
		out.write(buffer);
	}

}
