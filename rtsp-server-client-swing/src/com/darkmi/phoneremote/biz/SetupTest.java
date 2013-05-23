package com.darkmi.phoneremote.biz;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import org.apache.mina.core.session.IoSession;

import com.darkmi.phoneremote.entity.send.RequestHeader;
import com.darkmi.phoneremote.entity.send.RequestSetup;

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
			String smartCartdID = (String)session.getAttribute("smartCartdID");
			CyclicBarrier barrier = (CyclicBarrier)session.getAttribute("barrier");
			
			//setup
			RequestHeader rh = new RequestHeader();
			RequestSetup rs = new RequestSetup();

			rh.setProtocol(2);
			rh.setVersion(1);
			rh.setPreByte(239);

			rs.setMsg_ID("0600");
			rs.setUserId(userID);
			rs.setSmartCartdID(smartCartdID);
			rs.setType("05");
			
			byte[] body = rs.toBytes();

			rh.setBodyLength(body.length);
			byte[] head = rh.toBytes();

			byte[] request = ArraysUtil.concat(head, body);
			
			//等待所有线程都创建完毕
			barrier.await();
			session.write(request);
			session.setAttribute("setupTime", System.currentTimeMillis());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}
