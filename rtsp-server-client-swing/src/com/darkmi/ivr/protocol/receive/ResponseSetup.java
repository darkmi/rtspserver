package com.darkmi.ivr.protocol.receive;

import com.darkmi.ivr.entity.receive.ResponseEntityBase;
import com.darkmi.ivr.entity.receive.Setup;

import myUtil.CommonUtil;
import myUtil.ConversionUtil;

public class ResponseSetup extends ResponseBase{

	public ResponseSetup() {
	}

	@Override
	public ResponseEntityBase fillBody(){
		Setup setup = new Setup(super.getMsgType());
		
		byte[] msgid = CommonUtil.subset(super.getBody(), 0, 2);
		byte[] userid = CommonUtil.subset(super.getBody(), 2, 20);
		byte[] operationCode = CommonUtil.subset(super.getBody(), 22,1);
		
		setup.setRh(super.getRh());
		setup.setUserId(new String(userid));
		setup.setMsg_ID(ConversionUtil.toHexString(msgid));
		setup.setOperationCode(ConversionUtil.toHexString(operationCode));
		return setup;
	}

	
}
