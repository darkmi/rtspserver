package com.darkmi.phoneclient.tool;

import java.util.ArrayList;

import com.darkmi.view.TestEntity;


public class BizTool {

	public BizTool() {
	}

	private static long start = TestEntity.phoneClientStartPhoneNumber;
	private static long stop = TestEntity.phoneClientStopPhoneNumber;
	private static long step = TestEntity.phoneClientPhoneNumberStep;
	private static ArrayList<Long> phoneNumber = new ArrayList<Long>();
	@SuppressWarnings("unused")
	private static long buffer = 0l;

	private static long startca = TestEntity.phoneClientStartCANumber;
	private static long stopca = TestEntity.phoneClientStopCANumber;
	private static long stepca = TestEntity.phoneClientCAStep;
	private static ArrayList<Long> ca = new ArrayList<Long>();
	@SuppressWarnings("unused")
	private static long bufferca = 0l;
	
	/**
	 * 得到一个电话号码
	 * @return
	 */
	public static synchronized Long getUserID(){
		if(phoneNumber.isEmpty()){
			for(long i=start;i<=stop;i+=step){
				phoneNumber.add(i);
			}
		}
		Long userid = phoneNumber.get(0);
		phoneNumber.remove(0);
		return userid;
	}

	/**
	 * 得到一个ca卡号
	 * @return
	 */
	public static synchronized Long getCACode(){
		if(ca.isEmpty()){
			for(long i=startca;i<=stopca;i+=stepca){
				ca.add(i);
			}
		}
		Long userid = ca.get(0);
		ca.remove(0);
		return userid;
	}

}
