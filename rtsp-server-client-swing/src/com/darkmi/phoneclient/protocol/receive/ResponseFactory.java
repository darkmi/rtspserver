package com.darkmi.phoneclient.protocol.receive;



import com.darkmi.phoneclient.entity.receive.ResponseEntityBase;
import com.darkmi.phoneclient.entity.receive.ResponseHeader;
import com.darkmi.phoneclient.tool.MessageType;

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
		byte[] msgID = CommonUtil.subset(bs, 3, 2);
		byte[] bodyLength = CommonUtil.subset(bs, 5, 1);
		
		header.setPreByte(ConversionUtil.byte2Int(preByte));
		header.setProtocol(ConversionUtil.byte2Int(protocol));
		header.setVersion(ConversionUtil.byte2Int(version));
		header.setMsgID(MessageType.getMessageTypeById(ConversionUtil.toHexString(msgID)));
		header.setBodyLength(ConversionUtil.byte2Int(bodyLength));
		return header;
	}


	public static ResponseEntityBase createResponse(ResponseHeader head,byte[] b) {
		MessageType msgType = head.getMsgID();
		
		ResponseBase rb = null;
		
		switch (msgType) {
		case SETUP:
			rb = new ResponseSetup();
			break;
		case ANNOUNCE:
			rb = new ResponseStop();
			break;
		case SENDKEY:
			rb = new ResponseSendKey();
			break;
		case PLAY:
			rb = new ResponsePlay();
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
