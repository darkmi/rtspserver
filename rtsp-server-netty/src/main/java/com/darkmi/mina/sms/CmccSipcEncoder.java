package com.darkmi.mina.sms;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class CmccSipcEncoder extends ProtocolEncoderAdapter {

	private final Charset charset;

	public CmccSipcEncoder(Charset charset) {
		this.charset = charset;
	}

	@Override
	public void encode(IoSession session, Object message,ProtocolEncoderOutput out) throws Exception {
		SmsObject sms = (SmsObject) message;
		CharsetEncoder ce = charset.newEncoder();
		IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
		String statusLine = "M sip:wap.fetion.com.cn SIP-C/2.0";
		String sender = sms.getSender();
		String receiver = sms.getReceiver();
		String smsContent = sms.getMessage();
		buffer.putString(statusLine + '\n', ce);
		buffer.putString("S: " + sender + '\n', ce);
		buffer.putString("R: " + receiver + '\n', ce);
		buffer.putString("L: " + (smsContent.getBytes(charset).length)+ "\n",ce);
		buffer.putString(smsContent, ce);
		buffer.flip();
		out.write(buffer);
	}
}
