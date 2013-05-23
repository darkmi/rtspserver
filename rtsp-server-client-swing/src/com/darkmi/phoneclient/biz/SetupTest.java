package com.darkmi.phoneclient.biz;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import org.apache.mina.core.session.IoSession;

import com.darkmi.phoneclient.entity.send.RequestHeader;
import com.darkmi.phoneclient.entity.send.RequestSetup;
import com.darkmi.phoneclient.tool.MessageType;

import util.aa.ArraysUtil;

public class SetupTest implements Runnable {

	public SetupTest(IoSession session) {
		this.session = session;
	}

	private IoSession session;

	@Override
	public void run() {
		try {
			session.setAttribute("setupTime", System.currentTimeMillis());
			String userID = (String) session.getAttribute("userID");
			String smartCartdID = (String) session.getAttribute("smartCartdID");
			CyclicBarrier barrier = (CyclicBarrier) session
					.getAttribute("barrier");

			
			// setup
			RequestHeader rh = new RequestHeader();
			RequestSetup rs = new RequestSetup();

			rs.setSmartCartdID(smartCartdID);
			rs.setType("05");
			rs.setIsPassword(1);
			rs.setPassword(userID);

			byte[] body = rs.toBytes();

			rh.setMsgID(MessageType.SETUP.getMessageId());
			rh.setBodyLength(body.length);
			byte[] head = rh.toBytes();

			byte[] request = ArraysUtil.concat(head, body);

			// 等待所有线程都创建完毕
			barrier.await();
			session.write(request);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

	}

}
