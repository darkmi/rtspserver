package com.darkmi.phoneremote.protocol.receive;


import com.darkmi.phoneremote.entity.receive.ResponseEntityBase;
import com.darkmi.phoneremote.entity.receive.ResponseHeader;
import com.darkmi.phoneremote.tool.MessageType;

import myUtil.CommonUtil;
import myUtil.ConversionUtil;

/**
 * Description: 请求对象创建工厂 Copyright (c) 永新视博 All Rights Reserved.
 * 
 * @version 1.0 2011-4-19 上午09:00:57 laojiang created
 */
public class ResponseFactory {

	/**
	 * 创建request header
	 * @param bs
	 * @return
	 */
	public static ResponseHeader createResponseHeader(byte[] bs){
		ResponseHeader header = new ResponseHeader();
		byte[] preByte = CommonUtil.subset(bs, 0, 1);
		byte[] protocol = CommonUtil.subset(bs, 1, 1);
		byte[] version = CommonUtil.subset(bs, 2, 1);
		byte[] bodyLength = CommonUtil.subset(bs, 3, 1);
		
		header.setPreByte(ConversionUtil.byte2Int(preByte));
		header.setProtocol(ConversionUtil.byte2Int(protocol));
		header.setVersion(ConversionUtil.byte2Int(version));
		header.setBodyLength(ConversionUtil.byte2Int(bodyLength));
		return header;
	}


	public static ResponseEntityBase createResponse(ResponseHeader head,byte[] b) {
		MessageType msgType = MessageType.getMessageTypeById(ConversionUtil.toHexString(CommonUtil.subset(b, 0, 2)));
		
		ResponseBase rb = null;
		
		switch (msgType) {
		case SETUP:
			rb = new ResponseSetup();
			break;
		case TEARDOWN:
			rb = new ResponseStop();
			break;
		case ANNOUNCE:
			rb = new ResponseAnnounce();
			break;
		default:
			break;
		}

		rb.setBody(b);
		rb.setRh(head);
		rb.setMsgType(msgType);
		
		return rb.fillBody();
	}
}
