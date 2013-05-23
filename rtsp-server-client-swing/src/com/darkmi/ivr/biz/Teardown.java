package com.darkmi.ivr.biz;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import org.apache.mina.core.session.IoSession;

import com.darkmi.ivr.entity.send.RequestHeader;
import com.darkmi.ivr.entity.send.RequestSendkey;
import com.darkmi.ivr.entity.send.RequestSetup;
import com.darkmi.ivr.tool.DataContent;

import util.aa.ArraysUtil;

public class Teardown implements Runnable {

	public Teardown(IoSession session) {
		this.session = session;
	}

	private IoSession session;

	@Override
	public void run() {
		try {
			String userID = (String) session.getAttribute("userID");
			CyclicBarrier barrier = (CyclicBarrier) session
					.getAttribute("barrier");
			
			RequestHeader rh1 = new RequestHeader();
			RequestSendkey rs1 = new RequestSendkey();

			rs1.setMsg_ID("0202");
			rs1.setUserId(userID);
			rs1.setDataContent(DataContent.NX.getMessageId());
			byte[] body1 = rs1.toBytes();

			rh1.setProtocol(2);
			rh1.setVersion(1);
			rh1.setBodyLength(body1.length);

			byte[] head1 = rh1.toBytes();

			byte[] request1 = ArraysUtil.concat(head1, body1);

			barrier.await();
			session.write(request1);
			
			Thread.sleep(500);

			RequestHeader rh = new RequestHeader();
			RequestSetup rs = new RequestSetup();

			rh.setProtocol(2);
			rh.setVersion(1);

			rs.setMsg_ID("0202");
			rs.setUserId(userID);
			byte[] body = rs.toBytes();

			rh.setBodyLength(body.length);
			byte[] head = rh.toBytes();

			byte[] request = ArraysUtil.concat(head, body);

			// 等待所有线程都返回
			session.write(request);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}
