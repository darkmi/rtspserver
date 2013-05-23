package com.darkmi.phoneremote.protocol.receive;


import com.darkmi.phoneremote.entity.receive.ResponseEntityBase;
import com.darkmi.phoneremote.entity.receive.Setup;

import myUtil.CommonUtil;
import myUtil.ConversionUtil;

public class ResponseSetup extends ResponseBase{

	public ResponseSetup() {
	}

	@Override
	public ResponseEntityBase fillBody(){
		Setup setup = new Setup(super.getMsgType());
		
		byte[] msgid = CommonUtil.subset(super.getBody(), 0, 2);
		byte[] operationCode = CommonUtil.subset(super.getBody(), 2,1);
		byte[] scale = CommonUtil.subset(super.getBody(), 3, 1);
		
		setup.setRh(super.getRh());
		setup.setMsgID(ConversionUtil.toHexString(msgid));
		setup.setOperationCode(ConversionUtil.toHexString(operationCode));
		setup.setScale(Integer.toString(ConversionUtil.byte2Int(scale)));
		return setup;
	}
	
}
