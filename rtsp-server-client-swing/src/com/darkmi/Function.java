package com.darkmi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.darkmi.ivr.biz.SetupControl;
import com.darkmi.ivr.biz.VideoDemandControl;
import com.darkmi.phoneclient.biz.PCVideoDemandControl;
import com.darkmi.phoneremote.biz.PRSetupControl;
import com.darkmi.phoneremote.biz.PRVideoDemandControl;
import com.darkmi.view.Test;
import com.darkmi.view.TestEntity;


public class Function {
	public Function() {
	}

	public void execute() {
		SaveProperty.save();

		// 判断启动模块
		boolean isIVR = TestEntity.isIVR;
		boolean isPR = TestEntity.isPR;
		boolean isPC = TestEntity.isPC;

		ExecutorService es = Executors.newCachedThreadPool();
		// 启动IVR测试
		if (isIVR) {

			// 测试setup
			if (TestEntity.isIVRLogin) {
				Long iVRSetupCount = TestEntity.IVRLoginAmount;// ivr登陆测试数量
				es.execute(new SetupControl(iVRSetupCount));
			}
			// 测试点播
			if (TestEntity.isIVRVideoDemand) {
				Long iVRVideoDemandCount = TestEntity.IVRVideoDemandAmount;// ivr点播测试数量
				es.execute(new VideoDemandControl(iVRVideoDemandCount));
			}
		}

		// 启动手机遥控器测试
		if (isPR) {
			
			// 测试setup
			if (TestEntity.isPhoneRemoteLogin) {
				Long PRSetupCount = TestEntity.PhoneRemoteLoginAmount;// 手机遥控器登陆测试数量
				es.execute(new PRSetupControl(PRSetupCount));
			}
			// 测试点播
			if (TestEntity.isPhoneRemoteVideoDemand) {
				Long PRVideoDemandCount = TestEntity.PhoneRemoteVideoDemandAmount;// 手机遥控器点播测试数量
				es.execute(new PRVideoDemandControl(PRVideoDemandCount));
			}
		}

		// 启动手机客户端测试
		if (isPC) {
			
			// 测试setup
			if (TestEntity.isPhoneClientLogin) {
//				Long PCSetupCount = TestEntity.phoneClientLoginAmount;// 手机客户端登陆测试数量
//				es.execute(new PCSetupControl(PCSetupCount));
			}
			// 测试点播
			if (TestEntity.isPhoneClientVideoDemand) {
				Long PCVideoDemandCount = TestEntity.phoneClientVideoDemandAmount;// 手机客户端点播测试数量
				es.execute(new PCVideoDemandControl(PCVideoDemandCount));
			}
		}

		ScheduledExecutorService shutdown = Executors
				.newSingleThreadScheduledExecutor();

		shutdown.schedule(new shutdownThread(), TestEntity.AllTestTime, TimeUnit.MINUTES);// 控制结束
		
		
		new Thread(new finishTime(TestEntity.AllTestTime)).start();

	}
}


//更新结束时间
class finishTime implements Runnable {
	public finishTime(long fte) {
		this.fte = fte;
	}

	long fte;

	@Override
	public void run() {
			for (Long i = fte; i >= 0; i--) {
				Test.time(i.toString());
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	}
}

//结束测试
class shutdownThread implements Runnable {

	public shutdownThread() {
	}

	@Override
	public void run() {

		TestEntity.isClose = true;
//		System.exit(0);
	}

}