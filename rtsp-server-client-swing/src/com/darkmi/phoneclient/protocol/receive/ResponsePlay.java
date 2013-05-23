package com.darkmi.phoneclient.protocol.receive;

import com.darkmi.phoneclient.entity.receive.Play;
import com.darkmi.phoneclient.entity.receive.ResponseEntityBase;

import myUtil.CommonUtil;
import myUtil.ConversionUtil;

public class ResponsePlay extends ResponseBase{

	public ResponsePlay() {
	}

	@Override
	public ResponseEntityBase fillBody() {
		Play play = new Play(super.getMsgType());
		
		byte[] operationCodeEnum = CommonUtil.subset(super.getBody(), 0, 1);
		byte[] nptBegin = CommonUtil.subset(super.getBody(), 1,2);
		byte[] nptTotal = CommonUtil.subset(super.getBody(), 3, 2);
		
		String operationCode = ConversionUtil.toHexString(operationCodeEnum);
		int begin = ConversionUtil.byte2Int(nptBegin);
		int total = ConversionUtil.byte2Int(nptTotal);
		
		play.setRh(super.getRh());
		play.setOperationCode(operationCode);
		play.setNptBegin(begin);
		play.setNptBegin(total);
		
		return play;
	}

}
