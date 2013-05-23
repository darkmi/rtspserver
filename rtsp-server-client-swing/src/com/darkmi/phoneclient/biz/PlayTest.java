package com.darkmi.phoneclient.biz;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import myUtil.CommonUtil;

import org.apache.mina.core.session.IoSession;

import com.darkmi.phoneclient.entity.send.RequestHeader;
import com.darkmi.phoneclient.entity.send.RequestPlay;
import com.darkmi.phoneclient.tool.MessageType;


public class PlayTest implements Runnable {

	public PlayTest(IoSession session,Integer offeringID) {
		this.session = session;
		this.offeringid = offeringID;
	}

	private IoSession session;
	private Integer offeringid;
	
	@Override
	public void run() {
		
		try {
			CyclicBarrier barrier = (CyclicBarrier) session
					.getAttribute("barrier");

			// 发送键值
			RequestHeader rh1 = new RequestHeader();
			RequestPlay rs1 = new RequestPlay();

			rs1.setOfferingID(offeringid);
			rs1.setIsBookmark(0);

			byte[] body1 = rs1.toBytes();

			rh1.setMsgID(MessageType.PLAY.getMessageId());
			rh1.setBodyLength(body1.length);
			byte[] head1 = rh1.toBytes();

			byte[] request1 = CommonUtil.concat(head1, body1);

			// 等待所有线程都创建完毕
			barrier.await();
			session.write(request1);
			session.setAttribute("playTime", System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
		
	}

}
