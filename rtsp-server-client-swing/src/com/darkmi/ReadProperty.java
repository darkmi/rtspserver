package com.darkmi;

import java.util.HashMap;

import com.darkmi.view.TestEntity;


import myUtil.RWproperties;

public class ReadProperty {

	public ReadProperty() {
		HashMap<String, String> hm = RWproperties.readProperties("./test.properties");
		TestEntity.IVRIP = hm.get("IVRIP");
		TestEntity.IVRPort = hm.get("IVRPort");
		TestEntity.IVRStartPhoneNumber = Long.valueOf(hm.get("IVRStartPhoneNumber"));
		TestEntity.IVRStopPhoneNumber = Long.valueOf(hm.get("IVRStopPhoneNumber"));
		TestEntity.IVRPhoneNumberStep = Long.valueOf(hm.get("IVRPhoneNumberStep"));
		TestEntity.IVRStartCANumber = Long.valueOf(hm.get("IVRStartCANumber"));
		TestEntity.IVRStopCANumber = Long.valueOf(hm.get("IVRStopCANumber"));
		TestEntity.IVRCAStep = Long.valueOf(hm.get("IVRCAStep"));
		TestEntity.IVRLoginKeystoke = hm.get("IVRLoginKeystoke");
		TestEntity.IVRVideoDemandKeystoke = hm.get("IVRVideoDemandKeystoke");
		TestEntity.IVRPlayKeystoke = hm.get("IVRPlayKeystoke");
		TestEntity.IVRVideoDemandAmount = Long.valueOf(hm.get("IVRVideoDemandAmount"));
		TestEntity.IVRLoginAmount = Long.valueOf(hm.get("IVRLoginAmount"));

		TestEntity.phoneClientIP = hm.get("PhoneClientIP");
		TestEntity.phoneClientPort = hm.get("PhoneClientPort");
		TestEntity.phoneClientStartPhoneNumber = Long.valueOf(hm.get("PhoneClientStartPhoneNumber"));
		TestEntity.phoneClientStopPhoneNumber = Long.valueOf(hm.get("PhoneClientStopPhoneNumber"));
		TestEntity.phoneClientPhoneNumberStep = Long.valueOf(hm.get("PhoneClientPhoneNumberStep"));
		TestEntity.phoneClientStartCANumber = Long.valueOf(hm.get("PhoneClientStartCANumber"));
		TestEntity.phoneClientStopCANumber = Long.valueOf(hm.get("PhoneClientStopCANumber"));
		TestEntity.phoneClientCAStep = Long.valueOf(hm.get("PhoneClientCAStep"));
		TestEntity.offeringID = Long.valueOf(hm.get("offeringID"));
		TestEntity.phoneClientPlayKeystoke = hm.get("PhoneClientPlayKeystoke");
		TestEntity.phoneClientVideoDemandAmount = Long.valueOf(hm.get("PhoneClientVideoDemandAmount"));
		TestEntity.phoneClientLoginAmount = Long.valueOf(hm.get("PhoneClientLoginAmount"));

		TestEntity.PhoneRemoteIP = hm.get("PhoneRemoteIP");
		TestEntity.PhoneRemotePort = hm.get("PhoneRemotePort");
		TestEntity.PhoneRemoteStartPhoneNumber = Long.valueOf(hm.get("PhoneRemoteStartPhoneNumber"));
		TestEntity.PhoneRemoteStopPhoneNumber = Long.valueOf(hm.get("PhoneRemoteStopPhoneNumber"));
		TestEntity.PhoneRemotePhoneNumberStep = Long.valueOf(hm.get("PhoneRemotePhoneNumberStep"));
		TestEntity.PhoneRemoteStartCANumber = Long.valueOf(hm.get("PhoneRemoteStartCANumber"));
		TestEntity.PhoneRemoteStopCANumber = Long.valueOf(hm.get("PhoneRemoteStopCANumber"));
		TestEntity.PhoneRemoteCAStep = Long.valueOf(hm.get("PhoneRemoteCAStep"));
		TestEntity.PhoneRemoteLoginKeystoke = hm.get("PhoneRemoteLoginKeystoke");
		TestEntity.PhoneRemoteVideoDemandKeystoke = hm.get("PhoneRemoteVideoDemandKeystoke");
		TestEntity.PhoneRemotePlayKeystoke = hm.get("PhoneRemotePlayKeystoke");
		TestEntity.PhoneRemoteVideoDemandAmount = Long.valueOf(hm.get("PhoneRemoteVideoDemandAmount"));
		TestEntity.PhoneRemoteLoginAmount = Long.valueOf(hm.get("PhoneRemoteLoginAmount"));

		TestEntity.Timeout = Long.valueOf(hm.get("Timeout"));
		TestEntity.AllTestTime = Long.valueOf(hm.get("AllTestTime"));

		TestEntity.isIVRLogin = Boolean.valueOf(hm.get("isIVRLogin"));
		TestEntity.isIVRVideoDemand = Boolean.valueOf(hm.get("isIVRVideoDemand"));
		TestEntity.isPhoneRemoteLogin = Boolean.valueOf(hm.get("isPhoneRemoteLogin"));
		TestEntity.isPhoneRemoteVideoDemand = Boolean.valueOf(hm.get("isPhoneRemoteVideoDemand"));
		TestEntity.isPhoneClientLogin = Boolean.valueOf(hm.get("isPhoneClientLogin"));
		TestEntity.isPhoneClientVideoDemand = Boolean.valueOf(hm.get("isPhoneClientVideoDemand"));
		TestEntity.isIVR = Boolean.valueOf(hm.get("isIVR"));
		TestEntity.isPR = Boolean.valueOf(hm.get("isPR"));
		TestEntity.isPC = Boolean.valueOf(hm.get("isPC"));

	}

}
