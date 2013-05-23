package com.darkmi.ivr.biz;

import java.util.concurrent.ExecutorService;
import org.apache.mina.core.session.IoSession;

import com.darkmi.ivr.tool.DataContent;


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
		case '1':
			service.submit(new SendKey(session, DataContent.数字键0.getMessageId()));
			break;
		case '2':
			service.submit(new SendKey(session, DataContent.数字键2.getMessageId()));
			break;
		case '4':
			service.submit(new SendKey(session, DataContent.数字键4.getMessageId()));
			break;
		case '6':
			service.submit(new SendKey(session, DataContent.数字键6.getMessageId()));
			break;
		case '8':
			service.submit(new SendKey(session, DataContent.数字键8.getMessageId()));
			break;
		case '5':
			service.submit(new SendKey(session, DataContent.数字键5.getMessageId()));
			break;
		case '0':
			service.submit(new SendKey(session, DataContent.NX.getMessageId()));
			break;
		default:
			break;
		}
		
	}

}
