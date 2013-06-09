package com.darkmi.mina.command;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class CommandEncoder implements ProtocolEncoder {

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		// Serialization to string is already provided in RTSP messages.
		String val = message.toString();
		IoBuffer buf = IoBuffer.wrap(val.getBytes());
		out.write(buf);
	}

	@Override
	public void dispose(IoSession session) throws Exception {
	}
}
