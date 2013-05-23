package com.darkmi.phoneremote.biz;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import org.apache.mina.core.session.IoSession;

import com.darkmi.phoneremote.entity.send.RequestHeader;
import com.darkmi.phoneremote.entity.send.RequestStop;

import util.aa.ArraysUtil;

public class Teardown implements Runnable {
	public Teardown() {
	}

	public Teardown(IoSession session) {
		this.session = session;
	}

	private IoSession session = null;

	@Override
	public void run() {
		try {
			
			CyclicBarrier barrier = (CyclicBarrier)session.getAttribute("barrier");
			
			//停止服务
			RequestHeader rh3 = new RequestHeader();
			RequestStop rs3 = new RequestStop();
			
			rh3.setProtocol(2);
			rh3.setVersion(1);
			
			rs3.setMsg_ID("0602");
			byte[] body3 = rs3.toBytes();
			
			rh3.setBodyLength(body3.length);
			byte[] head3 = rh3.toBytes();
			
			byte[] request3 = ArraysUtil.concat(head3, body3);
			
			//等待所有线程都返回
			barrier.await();
			session.write(request3);
			session.setAttribute("teardownTime", System.currentTimeMillis());

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

	}

}
