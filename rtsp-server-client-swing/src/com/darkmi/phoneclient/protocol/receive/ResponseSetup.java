package com.darkmi.phoneclient.protocol.receive;



import com.darkmi.phoneclient.entity.receive.ResponseEntityBase;
import com.darkmi.phoneclient.entity.receive.Setup;

import myUtil.CommonUtil;
import myUtil.ConversionUtil;

public class ResponseSetup extends ResponseBase{

	public ResponseSetup() {
	}

	@Override
	public ResponseEntityBase fillBody(){
		Setup setup = new Setup(super.getMsgType());
		
		byte[] operationCodeEnum = CommonUtil.subset(super.getBody(), 0, 1);
		byte[] scale = CommonUtil.subset(super.getBody(), 1,1);
		byte[] channelId = CommonUtil.subset(super.getBody(), 2, 1);
		byte[] playMode = CommonUtil.subset(super.getBody(), 3, 1);
		byte[] loopMode = CommonUtil.subset(super.getBody(), 4, 1);
		byte[] playTimes = CommonUtil.subset(super.getBody(), 5, 1);
		byte[] offeringId = CommonUtil.subset(super.getBody(), 6, 1);
		byte[] nptBegin = CommonUtil.subset(super.getBody(), 7, 2);
		byte[] nptEnd = CommonUtil.subset(super.getBody(), 9, 2);
//		byte[] count = CommonUtil.subset(super.getBody(), 10, 1);//有问题
		
		setup.setRh(super.getRh());
		
//		setup.setAssetId(ConversionUtil.byte2Int());
		setup.setChannelID(ConversionUtil.byte2Int(channelId));
		setup.setLoopMode(ConversionUtil.byte2Int(loopMode));
		setup.setNptBegin(ConversionUtil.byte2Int(nptBegin));
		setup.setNptTotal(ConversionUtil.byte2Int(nptEnd)-ConversionUtil.byte2Int(nptBegin));
		setup.setOfferingID(ConversionUtil.byte2Int(offeringId));
		setup.setOperationCode(ConversionUtil.toHexString(operationCodeEnum));
		setup.setPlayMode(ConversionUtil.byte2Int(playMode));
		setup.setPlayTimes(ConversionUtil.byte2Int(playTimes));
		setup.setScale(ConversionUtil.byte2Int(scale));
		
		return setup;
	}
	
}
