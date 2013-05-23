package com.darkmi.mina.command;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class CommandCodecFactory implements ProtocolCodecFactory {

	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return new CommandDecoder();
	}

	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return new CommandEncoder();
	}
}
