package com.darkmi.ivr.biz;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.darkmi.LogEntity;
import com.darkmi.ivr.entity.receive.Announce;
import com.darkmi.ivr.entity.receive.ResponseEntityBase;
import com.darkmi.ivr.entity.receive.Setup;
import com.darkmi.ivr.tool.MessageType;

public class IvrHander implements IoHandler {

	public IvrHander() {
	}

	@Override
	public void exceptionCaught(IoSession arg0, Throwable arg1)
			throws Exception {

	}

	@Override
	public void messageReceived(IoSession arg0, Object arg1) throws Exception {
		long end = System.currentTimeMillis();
		LogEntity le = (LogEntity)arg0.getAttribute("logEntity");
		ResponseEntityBase response = (ResponseEntityBase) arg1;
		MessageType msgtype = response.msgType;
		switch (msgtype) {
		case SETUP:
			Setup rs = (Setup) response;
			String str = rs.getOperationCode();
			// System.out.println(str);
			if ("20".equals(str)) {
				le.addSuccess();
			} else {
				le.addFailed();
			}

			le.addPoerationCode(str);
			long start = (Long) arg0.getAttribute("setuptime");
			le.addResponseTime(end - start);
			break;
		case ANNOUNCE:
			Announce ann = (Announce) response;
			String str1 = ann.getOperationCode();
			if ("27".equals(str1) || "20".equals(str1) || "26".equals(str1)) {
				le.addSuccess();
			} else {
				le.addFailed();
			}

			le.addPoerationCode(str1);
			long videodemand = (Long) arg0.getAttribute("videodemand");
			le.addResponseTime(end - videodemand);
			break;
		default:
			break;
		// case ANNOUNCE:
		// Announce ann = (Announce)response;
		// String str1 = ann.getOperationCode();
		// if("21".equals(str1) || "20".equals(str1) || "27".equals(str1)){
		// ViewEntity.addSuccess();
		// }else{
		// ViewEntity.addFailed();
		// }
		// ViewEntity.addPoerationCode(str);
		// long start = (Long)arg0.getAttribute("setuptime");
		// ViewEntity.addResponseTime(end-start);
		// break;
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
