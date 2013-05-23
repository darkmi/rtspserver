package com.darkmi.phoneclient.biz;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import org.apache.mina.core.session.IoSession;

import com.darkmi.phoneclient.entity.send.RequestHeader;
import com.darkmi.phoneclient.tool.MessageType;

public class Teardown implements Runnable {

	public Teardown(IoSession session) {
		this.session = session;
	}

	private IoSession session;

	@Override
	public void run() {
		try {
			CyclicBarrier barrier = (CyclicBarrier) session
					.getAttribute("barrier");
			// 停止服务
			RequestHeader rh1 = new RequestHeader();

			rh1.setMsgID(MessageType.TEARDOWN.getMessageId());
			rh1.setBodyLength(0);
			byte[] head1 = rh1.toBytes();

			// 等待所有线程都返回
			barrier.await();
			session.write(head1);
			session.setAttribute("teardownTime", System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

	}

}
