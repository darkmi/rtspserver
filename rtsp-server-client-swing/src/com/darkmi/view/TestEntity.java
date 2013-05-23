package com.darkmi.view;

public class TestEntity {

	public TestEntity() {
	}
	
	public static String IVRIP="";
	public static String IVRPort="";
	public static Long IVRStartPhoneNumber=0l;
	public static Long IVRStopPhoneNumber=0l;
	public static Long IVRPhoneNumberStep=0l;
	public static Long IVRStartCANumber=0l;
	public static Long IVRStopCANumber=0l;
	public static Long IVRCAStep=0l;
	public static String IVRLoginKeystoke="";
	public static String IVRVideoDemandKeystoke="";
	public static String IVRPlayKeystoke="";
	public static Long IVRVideoDemandAmount=0l;
	public static Long IVRLoginAmount=0l;
	
	
	public static String phoneClientIP="";
	public static String phoneClientPort="";
	public static Long phoneClientStartPhoneNumber=0l;
	public static Long phoneClientStopPhoneNumber=0l;
	public static Long phoneClientPhoneNumberStep=0l;
	public static Long phoneClientStartCANumber=0l;
	public static Long phoneClientStopCANumber=0l;
	public static Long phoneClientCAStep=0l;
	public static String phoneClientPlayKeystoke="";
	public static Long phoneClientVideoDemandAmount=0l;
	public static Long phoneClientLoginAmount=0l;
	public static Long offeringID=0l;
	
	
	public static String PhoneRemoteIP="";
	public static String PhoneRemotePort="";
	public static Long PhoneRemoteStartPhoneNumber=0l;
	public static Long PhoneRemoteStopPhoneNumber=0l;
	public static Long PhoneRemotePhoneNumberStep=0l;
	public static Long PhoneRemoteStartCANumber=0l;
	public static Long PhoneRemoteStopCANumber=0l;
	public static Long PhoneRemoteCAStep=0l;
	public static String PhoneRemoteLoginKeystoke="";
	public static String PhoneRemoteVideoDemandKeystoke="";
	public static String PhoneRemotePlayKeystoke="";
	public static Long PhoneRemoteVideoDemandAmount=0l;
	public static Long PhoneRemoteLoginAmount=0l;
	
	public static Long Timeout=0l;
	public static Long AllTestTime=0l;

	
	public static Boolean isIVR=false;//是否启动IVR测试
	public static Boolean isPR=false;//是否启动手机遥控器测试
	public static Boolean isPC=false;//是否启动手机客户端测试
	
	public static Boolean isIVRLogin = false;//是否测试IVR登陆
	public static Boolean isIVRVideoDemand = false;//是否测试IVR点播
	public static Boolean isPhoneRemoteLogin = false;//是否测试手机遥控器登陆
	public static Boolean isPhoneRemoteVideoDemand = false;//是否测试手机遥控器点播
	public static Boolean isPhoneClientLogin = false;//是否测试手机客户端登陆
	public static Boolean isPhoneClientVideoDemand = false;//是否测试手机客户端点播
	
	public static boolean isClose = false;//是否停止测试

}
