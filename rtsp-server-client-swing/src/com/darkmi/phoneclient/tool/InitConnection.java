package com.darkmi.phoneclient.tool;

import myUtil.MinaUtil;
import org.apache.mina.core.session.IoSession;

import com.darkmi.phoneclient.biz.PCHander;
import com.darkmi.phoneclient.protocol.MessageCodecFactory;
import com.darkmi.view.TestEntity;


public class InitConnection {

	public InitConnection() {
	}

	
	/**
	 * 返回一个连接数组
	 * 连接只尝试一次，不保证连接成功个数为count
	 * @param count 准备连接个数
	 * @return
	 */
	public IoSession[] initConn(int count) {
		IoSession[] list = new IoSession[count];
		for (int i = 0; i < count; i++) {
			IoSession sess = this.conn();
			if (sess != null)
				list[i] = sess;
		}
		return list;
	}

	/**
	 * 得到一个连接，如果为null则为连接失败
	 * @return
	 */
	public IoSession conn() {
		try {
			IoSession session = new MinaUtil().ConnTcpByMina(TestEntity.phoneClientIP,
					TestEntity.phoneClientPort, new PCHander(),
					new MessageCodecFactory());
			return session;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * 修补连接个数
	 * @param sess 已经连接成功的连接数组
	 * @param count 准备达到预期的连接个数
	 * @return count个连接成功的数组
	 */
	public IoSession[] repair(IoSession[] sess,int count){
		IoSession[] fresh = new IoSession[count];
		IoSession[] list = new IoSession[count - sess.length];
		for(int i=0;i<list.length;i++){
			list[i] = this.conn();
		}
		
		System.arraycopy(sess, 0, fresh, 0, sess.length);
		System.arraycopy(list, 0, fresh, sess.length, list.length);
		
		return fresh;
	}

}
