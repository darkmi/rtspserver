package com.darkmi.phoneremote.protocol.receive;

import com.darkmi.phoneremote.entity.receive.ResponseEntityBase;
import com.darkmi.phoneremote.entity.receive.Stop;

import myUtil.CommonUtil;
import myUtil.ConversionUtil;

public class ResponseStop extends ResponseBase{

	public ResponseStop() {
	}

	@Override
	public ResponseEntityBase fillBody() {
		Stop stop = new Stop(super.getMsgType());
		
		byte[] msgid = CommonUtil.subset(super.getBody(), 0, 2);
		byte[] operationCode = CommonUtil.subset(super.getBody(), 2,1);
		byte[] reserve = CommonUtil.subset(super.getBody(), 3,1);
		
		stop.setRh(super.getRh());
		stop.setMsgID(ConversionUtil.toHexString(msgid));
		stop.setOperationCode(ConversionUtil.toHexString(operationCode));
		stop.setReserve(Integer.toString(ConversionUtil.byte2Int(reserve)));
		return stop;
	}

}
