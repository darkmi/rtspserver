package com.darkmi.phoneclient.biz;

import java.util.concurrent.ExecutorService;

import org.apache.mina.core.session.IoSession;

import com.darkmi.phoneclient.tool.DataContent;


public class SendCustomKey extends SendKey {

	public SendCustomKey(ExecutorService service, IoSession session, char key)
			throws InterruptedException {
		switch (key) {
		case '4':
			service.submit(new SendKey(session, DataContent.快退.getMessageId()));
			break;
		case '6':
			service.submit(new SendKey(session, DataContent.快进.getMessageId()));
			break;
		case '5':
			service.submit(new SendKey(session, DataContent.播放.getMessageId()));
			break;
		case '0':
			service.submit(new SendKey(session, DataContent.暂停.getMessageId()));
			break;
		default:
			break;
		}

	}
}
