package com.darkmi.mina.command;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class CommandDecoder extends CumulativeProtocolDecoder {

	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {

		// Remember the initial position.
		int start = in.position();

		// Now find the first CRLF in the buffer.
		byte previous = 0;
		while (in.hasRemaining()) {
			byte current = in.get();

			if (previous == '\r' && current == '\n') {
				// Remember the current position and limit.
				int position = in.position();
				int limit = in.limit();
				try {
					in.position(start);
					in.limit(position);
					// The bytes between in.position() and in.limit()
					// now contain a full CRLF terminated line.
					String str = new String(ioBufferToByte(in.slice()));
					out.write(str);
				} finally {
					// Set the position to point right after the
					// detected line and set the limit to the old
					// one.
					in.position(position);
					in.limit(limit);
				}
				
				// Decoded one line; CumulativeProtocolDecoder will
				// call me again until I return false. So just
				// return true until there are no more lines in the
				// buffer.
				
				return true;
			}
			previous = current;
		}

		// Could not find CRLF in the buffer. Reset the initial
		// position to the one we recorded above.
		in.position(start);

		return false;
	}

	public byte[] ioBufferToByte(Object message) {
		if (!(message instanceof IoBuffer)) {
			return null;
		}
		IoBuffer ioBuffer = (IoBuffer) message;
		byte[] b = new byte[ioBuffer.limit()];
		ioBuffer.get(b);
		return b;
	}

}
