package com.darkmi.phoneclient.protocol.receive;

import com.darkmi.phoneclient.entity.receive.ResponseEntityBase;
import com.darkmi.phoneclient.entity.receive.Sendkey;

import myUtil.CommonUtil;
import myUtil.ConversionUtil;

public class ResponseSendKey extends ResponseBase{

	public ResponseSendKey() {
	}

	@Override
	public ResponseEntityBase fillBody() {
		Sendkey rsk = new Sendkey(super.getMsgType());
		
		byte[] operationCodeEnum = CommonUtil.subset(super.getBody(), 0, 1);
		byte[] scale = CommonUtil.subset(super.getBody(), 1,1);
		byte[] nptBegin = CommonUtil.subset(super.getBody(), 2, 2);
		
		rsk.setRh(super.getRh());
		rsk.setOperationCode(ConversionUtil.toHexString(operationCodeEnum));
		rsk.setScale(ConversionUtil.byte2Int(scale));
		rsk.setNptBegin(ConversionUtil.byte2Int(nptBegin));
		
		return rsk;
	}

}
