package com.darkmi.ivr.protocol.receive;

import com.darkmi.ivr.entity.receive.ResponseEntityBase;
import com.darkmi.ivr.entity.receive.ResponseHeader;
import com.darkmi.ivr.tool.MessageType;

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
		byte[] protocol = CommonUtil.subset(bs, 0, 1);
		byte[] version = CommonUtil.subset(bs, 1, 1);
		byte[] bodyLength = CommonUtil.subset(bs, 2, 2);
		header.setProtocol(ConversionUtil.byte2Int(protocol));
		header.setVersion(ConversionUtil.byte2Int(version));
		header.setBodyLength(ConversionUtil.lh2short(bodyLength));
		return header;
	}


	public static ResponseEntityBase createResponse(ResponseHeader head,byte[] b) {
		MessageType msgType = MessageType.getKeyByValue(ConversionUtil.toHexString(CommonUtil.subset(b, 0, 2)));
		
		ResponseBase rb = null;
		
		switch (msgType) {
		case SETUP:
			rb = new ResponseSetup();
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
