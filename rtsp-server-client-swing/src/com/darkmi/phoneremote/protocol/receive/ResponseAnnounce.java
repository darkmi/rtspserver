package com.darkmi.phoneremote.protocol.receive;

import com.darkmi.phoneremote.entity.receive.Announce;
import com.darkmi.phoneremote.entity.receive.ResponseEntityBase;

import myUtil.CommonUtil;
import myUtil.ConversionUtil;

public class ResponseAnnounce extends ResponseBase{

	public ResponseAnnounce() {
	}

	@Override
	public ResponseEntityBase fillBody() {
		Announce announce = new Announce(super.getMsgType());
		
		byte[] msgid = CommonUtil.subset(super.getBody(), 0, 2);
		byte[] operationCode = CommonUtil.subset(super.getBody(), 2,1);
		
		announce.setRh(super.getRh());
		announce.setMsgID(ConversionUtil.toHexString(msgid));
		announce.setOperationCode(ConversionUtil.toHexString(operationCode));
		return announce;
	}

}
