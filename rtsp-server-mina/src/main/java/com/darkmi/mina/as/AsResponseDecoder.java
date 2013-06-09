package com.darkmi.mina.as;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class AsResponseDecoder extends CumulativeProtocolDecoder {

	public boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		if (in.remaining() > 0) { //�����ʱ����ȡ4�ֽ��ж���Ϣ����
			
			//ǰ�ĸ��ֽ�Ϊ��Ϣ����
			byte[] sizeBytes = new byte[4];
			in.mark();//��ǵ�ǰλ�ã��Ա�reset
			in.get(sizeBytes);//��ȡǰ4�ֽ�
			int size = NumberUtil.byteArrayToInt(sizeBytes);
			
			//�����Ϣ���ݵĳ��Ȳ�����ֱ�ӷ���true
			if (size > in.remaining()) { //�����Ϣ���ݲ����������ã��൱�ڲ���ȡsize
				in.reset();
				return false;//��������ݣ���ƴ�ճ��������
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
				if (in.remaining() > 0) { //����ȡ���ݺ�ճ�˰���ø����ٸ�һ�Σ�������һ�ν���
					return true;
				}
			}
		}
		return false;//����ɹ����ø�����н����¸���
	}
}