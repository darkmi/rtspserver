package com.darkmi.ivr.protocol.receive;

import com.darkmi.ivr.entity.receive.Announce;
import com.darkmi.ivr.entity.receive.ResponseEntityBase;

import myUtil.CommonUtil;
import myUtil.ConversionUtil;

public class ResponseAnnounce extends ResponseBase{

	public ResponseAnnounce() {
	}

	
	@Override
	public ResponseEntityBase fillBody(){
		Announce announce = new Announce(super.getMsgType());
		
		byte[] msgid = CommonUtil.subset(super.getBody(), 0, 2);
		byte[] userid = CommonUtil.subset(super.getBody(), 2, 20);
		byte[] Code = CommonUtil.subset(super.getBody(), 22,1);
		
		announce.setRh(super.getRh());
		announce.setUserId(new String(userid));
		announce.setMsg_ID(ConversionUtil.toHexString(msgid));
		announce.setOperationCode(ConversionUtil.toHexString(Code));
		return announce;
		
	}
	
}
