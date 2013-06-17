package com.darkmi.mina.as;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class AsResponseDecoder extends CumulativeProtocolDecoder {

	public boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		if (in.remaining() > 0) {
			
	
			byte[] sizeBytes = new byte[4];
			in.mark();
			int size = NumberUtil.byteArrayToInt(sizeBytes);
			

			if (size > in.remaining()) { 
				in.reset();
				return false;
			} else {
			
				byte[] bytes = new byte[size];
				in.get(bytes, 0, size);
				String xmlStr = new String(bytes, "UTF-8");
				System.out.println("------------" + xmlStr);
				if (null != xmlStr && xmlStr.length() > 0) {
					AsResponse resCmd = new AsResponse();
					AsXmlPacker.parse(resCmd, xmlStr);
					if (resCmd != null) {
						out.write(resCmd);
					}
				}
				if (in.remaining() > 0) {
					return true;
				}
			}
		}
		return false;
	}
}