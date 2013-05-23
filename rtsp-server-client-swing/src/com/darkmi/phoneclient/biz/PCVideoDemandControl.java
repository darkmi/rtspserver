package com.darkmi.phoneclient.biz;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.mina.core.session.IoSession;

import com.darkmi.LogEntity;
import com.darkmi.PrintLog;
import com.darkmi.phoneclient.tool.BizTool;
import com.darkmi.phoneclient.tool.InitConnection;
import com.darkmi.phoneclient.tool.OperationCodeEnum;
import com.darkmi.view.TestEntity;


public class PCVideoDemandControl implements Runnable {

	public PCVideoDemandControl(Long pCVideoDemandCount) {
		useramount = pCVideoDemandCount.intValue();
	}

	int useramount = 0;
	ExecutorService service = Executors.newCachedThreadPool();

	@Override
	public void run() {
		int flag = 1;
		IoSession[] session = null;
		try {
			while (true) {
				switch (flag) {
				case 1:// 连接session
					if (TestEntity.isClose) {
						return;
					}
					session = new InitConnection().initConn(useramount);
					this.print("创建连接");
					flag = 2;
					break;
				case 2:// 发送setup请求
					CyclicBarrier barrier = new CyclicBarrier(useramount);// 控制并发
					LogEntity le = new LogEntity(2);
					for (IoSession sess : session) {
						sess.setAttribute("barrier", barrier);
						sess.setAttribute("setup", le);
						sess.setAttribute("userID", BizTool.getUserID()
								.toString());
						sess.setAttribute("smartCartdID", BizTool.getCACode()
								.toString());
						service.submit(new SetupTest(sess));
					}
					this.print("发送请求");
					Thread.sleep(TestEntity.Timeout * 1000);
					PrintLog.print("PhoneClient-申请", le,
							OperationCodeEnum.getSelf(), "phoneclient",
							useramount);
					flag = 5;
					break;
				case 3:// 发送停止
					LogEntity le1 = new LogEntity(2);
					for (IoSession sess : session) {
						// sess.setAttribute("barrier", barrier1);
						sess.setAttribute("shutdown", le1);
						service.submit(new Teardown(sess));
					}
					this.print("发送停止");
					Thread.sleep(TestEntity.Timeout * 1000);
					PrintLog.print("PhoneClient-停止", le1,
							OperationCodeEnum.getSelf(), "phoneclient",
							useramount);
					flag = 1;
					break;
				case 5:// 播放
					LogEntity le2 = new LogEntity(2);
					int offeringid = TestEntity.offeringID.intValue();
					for (IoSession sess : session) {
						sess.setAttribute("play", le2);
						service.submit(new PlayTest(sess, offeringid));
					}
					this.print("播放");
					Thread.sleep(TestEntity.Timeout * 1000);
					PrintLog.print("PhoneClient-点播", le2,
							OperationCodeEnum.getSelf(), "phoneclient",
							useramount);
					flag = 6;
					break;
				case 6:// 发送自定义键
					String keys = TestEntity.phoneClientPlayKeystoke;
					if ("".equalsIgnoreCase(keys)) {
						flag = 3;
						break;
					}
					char[] chars = keys.toCharArray();
					for (char ch : chars) {
						for (IoSession sess : session) {
							new SendCustomKey(service, sess, ch);
						}
						Thread.sleep(TestEntity.Timeout * 1000);
					}
					this.print("点播后");
					flag = 3;
					break;
				default:
					break;
				}
				// 尝试补充连接个数
				if (session.length != useramount) {
					new InitConnection().repair(session, useramount);
				}
				Thread.sleep(500);
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	private void print(String str) {
		System.out.println("发送     " + str);
	}
}
