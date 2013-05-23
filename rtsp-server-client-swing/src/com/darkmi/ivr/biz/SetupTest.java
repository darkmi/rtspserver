package com.darkmi.ivr.biz;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import org.apache.mina.core.session.IoSession;

import com.darkmi.ivr.entity.send.RequestHeader;
import com.darkmi.ivr.entity.send.RequestSetup;

import util.aa.ArraysUtil;

public class SetupTest implements Runnable {

	public SetupTest(IoSession session) {
		this.session = session;
	}

	private IoSession session;
	

	@Override
	public void run() {
		try {
			String userID = (String) session.getAttribute("userID");
			CyclicBarrier barrier = (CyclicBarrier)session.getAttribute("barrier");
			
			RequestHeader rh = new RequestHeader();
			RequestSetup rs = new RequestSetup();

			rh.setProtocol(2);
			rh.setVersion(1);

			rs.setMsg_ID("0200");
			rs.setUserId(userID);
			byte[] body = rs.toBytes();

			rh.setBodyLength(body.length);
			byte[] head = rh.toBytes();

			byte[] request = ArraysUtil.concat(head, body);
			
			//等待所有线程都创建完毕
			barrier.await();
			session.write(request);
			session.setAttribute("setuptime", System.currentTimeMillis());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
