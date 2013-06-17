package com.darkmi.mina.as;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class AsResponseEncoder extends ProtocolEncoderAdapter {
	private final Charset charset;

	public AsResponseEncoder(Charset charset) {
		this.charset = charset;
	}

	@SuppressWarnings("unused")
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		CharsetEncoder ce = charset.newEncoder();
		IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);

		AsResponse respCmd = (AsResponse) message;

		String xml = AsXmlPacker.pack(respCmd);
		byte[] bytes = xml.getBytes();
		byte[] sizeBytes = NumberUtil.intToByteArray(bytes.length);

		buffer.put(sizeBytes);
		buffer.put(bytes);
		buffer.flip();
		out.write(buffer);
	}

}
