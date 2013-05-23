package com.darkmi.phoneremote.biz;

import java.util.concurrent.ExecutorService;
import org.apache.mina.core.session.IoSession;

import com.darkmi.phoneremote.tool.DataContent;

/**
 * 把输入的2468转换并发送响应的按键
 * 
 * @author Administrator
 * 
 */
public class SendCustomKey extends SendKey {

	public SendCustomKey(ExecutorService service, IoSession session, char key)
			throws InterruptedException {
		switch (key) {
		case '4':
			service.submit(new SendKey(session, DataContent.左.getMessageId()));
			break;
		case '6':
			service.submit(new SendKey(session, DataContent.右.getMessageId()));
			break;
		case '8':
			service.submit(new SendKey(session, DataContent.下.getMessageId()));
			break;
		case '2':
			service.submit(new SendKey(session, DataContent.上.getMessageId()));
			break;
		case '5':
			service.submit(new SendKey(session, DataContent.确定.getMessageId()));
			break;
		case '0':
			service.submit(new SendKey(session, DataContent.首页.getMessageId()));
			break;
		default:
			break;
		}
		
	}

}
