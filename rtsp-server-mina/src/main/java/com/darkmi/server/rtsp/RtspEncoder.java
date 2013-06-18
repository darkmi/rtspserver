package com.darkmi.server.rtsp;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class RtspEncoder extends ProtocolEncoderAdapter {
	//private static Logger logger = LoggerFactory.getLogger(RtspEncoder.class);
	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		String request = message.toString();
		byte[] bs = request.getBytes();
		IoBuffer buffer = IoBuffer.allocate(bs.length, false);
		buffer.put(bs);
		buffer.flip();
		out.write(buffer);
	}
}
