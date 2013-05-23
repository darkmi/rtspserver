package com.darkmi.ivr.biz;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.apache.mina.core.session.IoSession;

import com.darkmi.ivr.entity.send.RequestHeader;
import com.darkmi.ivr.entity.send.RequestSendkey;

import util.aa.ArraysUtil;

public class SendKey implements Runnable {
	
	public SendKey(){}

	public SendKey(IoSession session, String key) {
		this.session = session;
		this.key = key;
	}

	private IoSession session;
	private String key = "";

	@Override
	public void run() {
		try {
			String userID = (String) session.getAttribute("userID");
			CyclicBarrier barrier = (CyclicBarrier) session
					.getAttribute("barrier");

			RequestHeader rh1 = new RequestHeader();
			RequestSendkey rs1 = new RequestSendkey();

			rs1.setMsg_ID("0201");
			rs1.setUserId(userID);
			rs1.setDataContent(key);
			byte[] body1 = rs1.toBytes();

			rh1.setProtocol(2);
			rh1.setVersion(1);
			rh1.setBodyLength(body1.length);

			byte[] head1 = rh1.toBytes();

			byte[] request1 = ArraysUtil.concat(head1, body1);

			barrier.await();
			session.write(request1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}
