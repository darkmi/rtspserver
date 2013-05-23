package com.darkmi.apm.request;

/**
 * Description: 
 * Copyright (c) 永新视博
 * All Rights Reserved.
 * @version 1.0  2012-7-20 上午09:20:11 MiXiaohui created
 */
public enum URIEnum {
	TransferContent, 
	GetTransferStatus, 
	CancelTransfer, 
	ExposeContent, 
	GetContentChecksum,
	GetContentInfo, 
	DeleteContent, 
	GetVolumeInfo;
	private URIEnum() {
	}

}
