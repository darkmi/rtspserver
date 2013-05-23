package com.darkmi.phoneclient.protocol.receive;

import com.darkmi.phoneclient.entity.receive.ResponseEntityBase;
import com.darkmi.phoneclient.entity.receive.Stop;

import myUtil.CommonUtil;
import myUtil.ConversionUtil;

public class ResponseStop extends ResponseBase{

	public ResponseStop() {
	}

	@Override
	public ResponseEntityBase fillBody() {
		Stop stop = new Stop(super.getMsgType());
		
		byte[] operationCodeEnum = CommonUtil.subset(super.getBody(), 0, 1);
		
		stop.setRh(super.getRh());
		stop.setOperationCode(ConversionUtil.toHexString(operationCodeEnum));
		return stop;
	}
}
