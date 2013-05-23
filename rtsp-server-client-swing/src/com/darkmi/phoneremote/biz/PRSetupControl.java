package com.darkmi.phoneremote.biz;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.mina.core.session.IoSession;

import com.darkmi.LogEntity;
import com.darkmi.PrintLog;
import com.darkmi.phoneremote.tool.BizTool;
import com.darkmi.phoneremote.tool.InitConnection;
import com.darkmi.phoneremote.tool.OperationCodeEnum;
import com.darkmi.view.TestEntity;


public class PRSetupControl implements Runnable {

	public PRSetupControl(Long PRSetupCount) {
		this.useramount = PRSetupCount.intValue();
	}

	int useramount = 0;
	ExecutorService service = Executors.newCachedThreadPool();

	@Override
	public void run() {
		CyclicBarrier barrier = new CyclicBarrier(useramount);// 控制并发
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
					flag = 2;
					break;
				case 2:// 发送setup请求
					LogEntity le = new LogEntity(3);
					for (IoSession sess : session) {
						sess.setAttribute("logEntity", le);
						sess.setAttribute("barrier", barrier);
						sess.setAttribute("userID", BizTool.getUserID()
								.toString());
						sess.setAttribute("smartCartdID", BizTool.getCACode()
								.toString());
						service.submit(new SetupTest(sess));
						System.out.println(sess+"----------发送setup请求");
					}
					Thread.sleep(TestEntity.Timeout*1000);
					PrintLog.print("PhoneRemote-申请", le,
							OperationCodeEnum.getSelf(),"phoneremote",useramount);
					flag = 5;
					break;
				case 3:// 发送停止
					LogEntity le1 = new LogEntity(3);
					for (IoSession sess : session) {
						sess.setAttribute("logEntity", le1);
						service.submit(new Teardown(sess));
						System.out.println(sess+"--------发送停止");
					}
					Thread.sleep(TestEntity.Timeout*1000);
					PrintLog.print("PhoneRemote-停止", le1,
							OperationCodeEnum.getSelf(),"phoneremote",useramount);
					flag = 4;
					break;
				case 4:// 销毁session
					for (IoSession sess : session) {
						sess.close(true);
						System.out.println(sess+"--------销毁session");
					}
					flag = 1;
					break;
				case 5:// 发送键值
					// 拿到需要输入的键集合
					String keys = TestEntity.PhoneRemoteLoginKeystoke;
					char[] chars = keys.toCharArray();
					for (char ch : chars) {
						for (IoSession sess : session) {
							new SendCustomKey(service, sess, ch);
							System.out.println(sess+"-------发送------"+ch);
						}
						Thread.sleep(500);
					}
					flag = 3;
					break;
				default:
					break;
				}
				Thread.sleep(TestEntity.Timeout*1000);
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

}
