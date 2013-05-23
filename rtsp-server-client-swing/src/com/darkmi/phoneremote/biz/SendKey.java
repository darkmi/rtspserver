package com.darkmi.phoneremote.biz;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import org.apache.mina.core.session.IoSession;

import com.darkmi.phoneremote.entity.send.RequestHeader;
import com.darkmi.phoneremote.entity.send.RequestSendkey;

import util.aa.ArraysUtil;

public class SendKey implements Runnable {
	
	public SendKey(){}

	public SendKey(IoSession session,String sendKeyValue) {
		this.session = session;
		this.sendkeyvalue = sendKeyValue;
	}

	private IoSession session;
	private String sendkeyvalue;

	@Override
	public void run() {
		try {
			CyclicBarrier barrier = (CyclicBarrier) session
					.getAttribute("barrier");

			RequestHeader rh1 = new RequestHeader();
			RequestSendkey rs1 = new RequestSendkey();

			rs1.setMsg_ID("0601");
			rs1.setDataContent(sendkeyvalue);
			rs1.setFrequency("1");
			byte[] body1 = rs1.toBytes();

			rh1.setProtocol(2);
			rh1.setVersion(1);
			rh1.setBodyLength(body1.length);

			byte[] head1 = rh1.toBytes();

			byte[] request1 = ArraysUtil.concat(head1, body1);

			barrier.await();
			session.write(request1);
			session.setAttribute("videodemand", System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}
