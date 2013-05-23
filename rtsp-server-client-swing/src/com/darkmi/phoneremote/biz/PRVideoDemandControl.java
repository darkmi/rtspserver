package com.darkmi.phoneremote.biz;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.mina.core.session.IoSession;

import com.darkmi.LogEntity;
import com.darkmi.PrintLog;
import com.darkmi.phoneremote.tool.BizTool;
import com.darkmi.phoneremote.tool.DataContent;
import com.darkmi.phoneremote.tool.InitConnection;
import com.darkmi.phoneremote.tool.OperationCodeEnum;
import com.darkmi.view.TestEntity;


public class PRVideoDemandControl implements Runnable {

	public PRVideoDemandControl(Long PRSetupCount) {
		useramount = PRSetupCount.intValue();
	}

	int useramount = 0;
	ExecutorService service = Executors.newCachedThreadPool();
	public static final Lock setupLock = new ReentrantLock();

	@Override
	public void run() {
		int flag = 1;
		IoSession[] session = null;
		try {
			while (true) {
				switch (flag) {
				case 1:// 连接session
					
						session = new InitConnection().initConn(useramount);
					this.print("建立连接");
					flag = 2;
					break;
				case 2:// 发送setup请求
					CyclicBarrier barrier = new CyclicBarrier(useramount);// 控制并发
					LogEntity le = new LogEntity(3);
					for (IoSession sess : session) {
						sess.setAttribute("barrier", barrier);
						sess.setAttribute("logEntity", le);
						sess.setAttribute("userID", BizTool.getUserID()
								.toString());
						sess.setAttribute("smartCartdID", BizTool.getCACode()
								.toString());
						service.submit(new SetupTest(sess));
					}
					this.print("申请服务");
					Thread.sleep(TestEntity.Timeout * 1000);
					PrintLog.print("PhoneRemote-申请", le,
							OperationCodeEnum.getSelf(), "phoneremote",useramount);
					flag = 7;
					break;
				case 3:// 发送停止
					LogEntity le1 = new LogEntity(3);
					for (IoSession sess : session) {
						sess.setAttribute("logEntity", le1);
						service.submit(new Teardown(sess));
					}
					Thread.sleep(TestEntity.Timeout * 1000);
					PrintLog.print("PhoneRemote-停止", le1,
							OperationCodeEnum.getSelf(), "phoneremote",useramount);
					this.print("停止");
					flag = 4;
					break;
				case 4:// 销毁session
					for (IoSession sess : session) {
						sess.close(true);
					}
					this.print("销毁");
					return;
				case 5:// 发送5
					LogEntity le2 = new LogEntity(3);
					for (IoSession sess : session) {
						sess.setAttribute("logEntity", le2);
						service.submit(new SendKey(sess, DataContent.确定
								.getMessageId()));
					}
					this.print("点播");
					Thread.sleep(TestEntity.Timeout * 1000);
					PrintLog.print("PhoneRemote-播放", le2,
							OperationCodeEnum.getSelf(), "phoneremote",useramount);
					flag = 8;
					break;
				case 7:// 发送自定义按键(播放前)
						// 拿到需要输入的键集合
					String keys = TestEntity.PhoneRemoteVideoDemandKeystoke;
					char[] chars = keys.toCharArray();
					for (char ch : chars) {
						for (IoSession sess : session) {
							new SendCustomKey(service, sess, ch);
						}
					}
					this.print("播放前");
					flag = 5;
					break;
				case 8:// 发送自定义按键(播放后)
						// 拿到需要输入的键集合
					String keys1 = TestEntity.PhoneRemotePlayKeystoke;
					char[] chars1 = keys1.toCharArray();
					for (char ch : chars1) {
						for (IoSession sess : session) {
							new SendCustomKey(service, sess, ch);
						}
						Thread.sleep(TestEntity.Timeout*1000);
					}
					this.print("播放后");
					flag = 9;
					break;
				case 9://  返回页面
					LogEntity le3 = new LogEntity(3);
					for (IoSession sess : session) {
						sess.setAttribute("logEntity", le3);
						sess.setAttribute("setupTime", System.currentTimeMillis());
						service.submit(new SendKey(sess, DataContent.上一级
								.getMessageId()));
					}
					this.print("返回页面");
					Thread.sleep(TestEntity.Timeout * 1000);
					PrintLog.print("PhoneRemote-返回首页", le3,
							OperationCodeEnum.getSelf(), "phoneremote",useramount);
					if (TestEntity.isClose) {
						flag = 3;
					}else{
						flag = 7;
					}
					Thread.sleep(TestEntity.Timeout*1000);
					break;
				default:
					break;
				}
				//尝试补充连接个数
				if(session.length != useramount){
					new InitConnection().repair(session, useramount);
				}
				Thread.sleep(1000);
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	private void print(String str){
//		System.out.println("发送     "+str);
	}

}
