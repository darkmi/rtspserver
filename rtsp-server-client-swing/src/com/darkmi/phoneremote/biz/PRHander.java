package com.darkmi.phoneremote.biz;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.darkmi.LogEntity;
import com.darkmi.phoneremote.entity.receive.Announce;
import com.darkmi.phoneremote.entity.receive.ResponseEntityBase;
import com.darkmi.phoneremote.entity.receive.Setup;
import com.darkmi.phoneremote.entity.receive.Stop;
import com.darkmi.phoneremote.tool.MessageType;

public class PRHander implements IoHandler{

	public PRHander() {
	}

	@Override
	public void exceptionCaught(IoSession arg0, Throwable arg1)
			throws Exception {
		
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void messageReceived(IoSession arg0, Object arg1) throws Exception {
		long end = System.currentTimeMillis();
		LogEntity le = (LogEntity)arg0.getAttribute("logEntity");
		
		ResponseEntityBase response = (ResponseEntityBase)arg1;
		MessageType msgtype = response.msgType;
		switch(msgtype){
		case SETUP://申请服务
			Setup rs = (Setup)response;
			String str = rs.getOperationCode();
			if("60".equals(str)){
				le.addSuccess();
			}else{
				le.addFailed();
			}
			
			le.addPoerationCode(str);
			Long setupTime = (Long)arg0.getAttribute("setupTime");
			le.addResponseTime(end - setupTime);
			break;
		case TEARDOWN://停止服务
			Stop ann = (Stop)response;
			String str1 = ann.getOperationCode();
			if("60".equals(str1)){
				le.addSuccess();
			}else{
				le.addFailed();
			}
			le.addPoerationCode(str1);
			Long teardownTime = (Long)arg0.getAttribute("teardownTime");
			le.addResponseTime(end - teardownTime);
			break;
		case ANNOUNCE://系统消息
			Announce tea = (Announce)response;
			String str2 = tea.getOperationCode();
			if("61".equals(str2)){
				le.addSuccess();
			}else{
				le.addFailed();
			}
			le.addPoerationCode(str2);
			Long videodemand = (Long)arg0.getAttribute("videodemand");
			le.addResponseTime(end - videodemand);
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
