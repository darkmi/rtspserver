package com.darkmi.mina.as;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class AsResponseDecoder extends CumulativeProtocolDecoder {
	/**
	 * 这个方法的返回值是重点：
	 * 1、当内容刚好时，返回false，告知父类接收下一批内容
	 * 2、内容不够时需要下一批发过来的内容，此时返回false，这样父类CumulativeProtocolDecoder
	 *    会将内容放进IoSession中，等下次来数据后就自动拼装再交给本类的doDecode
	 * 3、当内容多时，返回true，因为需要再将本批数据进行读取，父类会将剩余的数据再次推送本类的doDecode
	 */
	public boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		if (in.remaining() > 0) { //有数据时，读取4字节判断消息长度
			
			//前四个字节为消息长度
			byte[] sizeBytes = new byte[4];
			in.mark();//标记当前位置，以便reset
			in.get(sizeBytes);//读取前4字节
			int size = NumberUtil.byteArrayToInt(sizeBytes);
			
			//如果消息内容的长度不够则直接返回true
			if (size > in.remaining()) { //如果消息内容不够，则重置，相当于不读取size
				in.reset();
				return false;//接收新数据，以拼凑成完整数据
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
				if (in.remaining() > 0) { //如果读取内容后还粘了包，就让父类再给俺一次，进行下一次解析
					return true;
				}
			}
		}
		return false;//处理成功，让父类进行接收下个包
	}
}