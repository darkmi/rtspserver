package com.darkmi.phoneclient.biz;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.darkmi.LogEntity;
import com.darkmi.phoneclient.entity.receive.Play;
import com.darkmi.phoneclient.entity.receive.ResponseEntityBase;
import com.darkmi.phoneclient.entity.receive.Setup;
import com.darkmi.phoneclient.entity.receive.Stop;
import com.darkmi.phoneclient.tool.MessageType;



public class PCHander implements IoHandler{

	public PCHander() {
	}

	@Override
	public void exceptionCaught(IoSession arg0, Throwable arg1)
			throws Exception {
		
	}

	@Override
	public void messageReceived(IoSession arg0, Object arg1) throws Exception {
		long end = System.currentTimeMillis();
		
		ResponseEntityBase response = (ResponseEntityBase)arg1;
		MessageType msgtype = response.msgType;
		switch(msgtype){
		case SETUP:
			LogEntity le = (LogEntity)arg0.getAttribute("setup");
			Setup rs = (Setup)response;
			String str = rs.getOperationCode();
			if("50".equals(str) || "51".equals(str)||"5D".equals(str)){
				le.addSuccess();
			}else{
				le.addFailed();
			}
			le.addPoerationCode(str);
			Long setuptime = (Long)arg0.getAttribute("setupTime");
			le.addResponseTime(end - setuptime);
			break;
		case PLAY:
			LogEntity ple = (LogEntity)arg0.getAttribute("play");
			Play play = (Play)response;
			String stt = play.getOperationCode();
			if("50".equals(stt)){
				ple.addSuccess();
			}else{
				ple.addFailed();
			}
			ple.addPoerationCode(stt);
			Long playTime = (Long)arg0.getAttribute("playTime");
			ple.addResponseTime(end - playTime);
			break;
		case SENDKEY:
//			Sendkey sk = (Sendkey)response;
//			String st = sk.getOperationCode();
//			if("50".equals(st)){
//				le.addSuccess();
//			}else{
//				le.addFailed();
//			}
//			le.addPoerationCode(st);
			break;
		case TEARDOWN:
//			Stop ann = (Stop)response;
//			String str1 = ann.getOperationCode();
//			if("50".equals(str1)){
//				le.addSuccess();
//			}else{
//				le.addFailed();
//			}
//			le.addPoerationCode(str1);
//			Long teardownTime = (Long)arg0.getAttribute("teardownTime");
//			le.addResponseTime(end - teardownTime);
			break;
		case ANNOUNCE:
			LogEntity sle = (LogEntity)arg0.getAttribute("shutdown");
				Stop ann = (Stop)response;
				String str1 = ann.getOperationCode();
				if("65".equals(str1)){
					sle.addSuccess();
				}else{
					sle.addFailed();
				}
				sle.addPoerationCode(str1);
				Long teardownTime = (Long)arg0.getAttribute("teardownTime");
				sle.addResponseTime(end - teardownTime);
				arg0.close(true);
				break;
				
		}
	}

	@Override
	public void messageSent(IoSession arg0, Object arg1) throws Exception {
		
	}

	@Override
	public void sessionClosed(IoSession arg0) throws Exception {
		
	}

	@Override
	public void sessionCreated(IoSession arg0) throws Exception {
		
	}

	@Override
	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
		
	}

	@Override
	public void sessionOpened(IoSession arg0) throws Exception {
		
	}

}
