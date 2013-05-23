package com.darkmi.phoneclient.biz;

public class PCSetupControl implements Runnable {
//
//	public PCSetupControl() {
//	}
//
//	public PCSetupControl(Long pCSetupCount) {
//		useramount = pCSetupCount.intValue();
//	}
//	int useramount=0;
//	ExecutorService service = Executors.newCachedThreadPool();
//	public static final Lock setupLock = new ReentrantLock();
//	CountDownLatch latch = new CountDownLatch(3);
	
	@Override
	public void run() {
//		int flag = 1;
//		IoSession[] session = null;
//		try {
//			while (true) {
//				if (TestEntity.isClose) {
//					return;
//				}
//					switch (flag) {
//					case 1://连接session
//						session = new InitConnection().initConn(useramount);
//						flag =2;
//						break;
//					case 2://发送setup请求
//						CyclicBarrier barrier = new CyclicBarrier(useramount);// 控制并发
//						CountDownLatch latch = new CountDownLatch(useramount);//控制流程
//						LogEntity le = new LogEntity(2);
//						for(IoSession sess : session){
//							sess.setAttribute("barrier", barrier);
//							sess.setAttribute("latch", latch);
//							sess.setAttribute("logEntity", le);
//							service.submit(new SetupTest(sess));
//						}
//						latch.await();
//						PrintLog.print("PhoneClient-setup", le,OperationCodeEnum.getSelf(),"phoneclient");
//						flag =3;
//						break;
//					case 3://发送停止
////						CyclicBarrier barrier1 = new CyclicBarrier(useramount);// 控制并发
//						CountDownLatch latch1 = new CountDownLatch(useramount);//控制流程
//						LogEntity le1 = new LogEntity(2);
//						for(IoSession sess : session){
////							sess.setAttribute("barrier", barrier1);
//							sess.setAttribute("logEntity", le1);
//							sess.setAttribute("latch", latch1);
//							service.submit(new Teardown(sess));
//						}
//						latch1.await();
//						PrintLog.print("PhoneClient-teardown", le1,OperationCodeEnum.getSelf(),"phoneclient");
//						flag =4;
//						break;
//					case 4://销毁session
//						for(IoSession sess : session){
//							sess.close(true);
//						}
//						flag =1;
//						break;
//					default:
//						break;
//					}
//					Thread.sleep(1000);
//			}
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
	}
}
