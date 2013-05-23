package com.darkmi.phoneremote.protocol.receive;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.darkmi.phoneremote.entity.receive.ResponseEntityBase;
import com.darkmi.phoneremote.entity.receive.ResponseHeader;



public class ResponseDecoder extends CumulativeProtocolDecoder {

	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {

		ResponseHeader header = null;
		byte[] head = new byte[ResponseHeader.LENGTH];
		in.get(head);// 读取前4字节
		in.mark();// 做个标识
		if (header == null) {
			header = ResponseFactory.createResponseHeader(head);
		}

		if (header.getBodyLength() > in.remaining()) { // 如果消息内容不够，则重置，相当于不读取size
			in.reset();// 返回标识位置
			return false;// 接收新数据，以拼凑成完整数据
		} else {
			byte[] body = new byte[header.getBodyLength()];
			in.get(body, 0, header.getBodyLength());
			ResponseEntityBase response = ResponseFactory.createResponse(header,body);
		    out.write(response);

			if (in.remaining() > 0) { // 如果读取内容后还粘了包，就让父类再给俺一次，进行下一次解析
				return true;
			}
		}
		return false;// 处理成功，让父类进行接收下个包
	}
}
