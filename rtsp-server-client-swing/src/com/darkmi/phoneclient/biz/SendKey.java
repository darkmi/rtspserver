package com.darkmi.phoneclient.biz;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import myUtil.CommonUtil;
import org.apache.mina.core.session.IoSession;

import com.darkmi.phoneclient.entity.send.RequestHeader;
import com.darkmi.phoneclient.entity.send.RequestSendkey;
import com.darkmi.phoneclient.tool.MessageType;

public class SendKey implements Runnable {

	public SendKey(){}
	
	public SendKey(IoSession session,String sendKeyValue) {
		this.session = session;
		this.key = sendKeyValue;
	}

	private IoSession session;
	private String key;

	@Override
	public void run() {
		try {
			CyclicBarrier barrier = (CyclicBarrier) session
					.getAttribute("barrier");

			// 发送键值
			RequestHeader rh1 = new RequestHeader();
			RequestSendkey rs1 = new RequestSendkey();

			rs1.setDataContent(key);
			rs1.setNptBegin(0);

			byte[] body1 = rs1.toBytes();

			rh1.setMsgID(MessageType.SENDKEY.getMessageId());
			rh1.setBodyLength(body1.length);
			byte[] head1 = rh1.toBytes();

			byte[] request1 = CommonUtil.concat(head1, body1);

			// 等待所有线程都创建完毕
			barrier.await();
			session.write(request1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

	}

}
