package com.darkmi;

import com.darkmi.view.TestEntity;

import myUtil.RWproperties;

public class SaveProperty {

	public SaveProperty() {
	}

	private static final String path = "./test.properties";

	public static void save() {
		RWproperties
				.writeProperties(path, "IVRIP", TestEntity.IVRIP.toString());
		RWproperties.writeProperties(path, "IVRPort",
				TestEntity.IVRPort.toString());
		RWproperties.writeProperties(path, "IVRStartPhoneNumber",
				TestEntity.IVRStartPhoneNumber.toString());
		RWproperties.writeProperties(path, "IVRStopPhoneNumber",
				TestEntity.IVRStopPhoneNumber.toString());
		RWproperties.writeProperties(path, "IVRPhoneNumberStep",
				TestEntity.IVRPhoneNumberStep.toString());
		RWproperties.writeProperties(path, "IVRStartCANumber",
				TestEntity.IVRStartCANumber.toString());
		RWproperties.writeProperties(path, "IVRStopCANumber",
				TestEntity.IVRStopCANumber.toString());
		RWproperties.writeProperties(path, "IVRCAStep",
				TestEntity.IVRCAStep.toString());
		RWproperties.writeProperties(path, "IVRLoginKeystoke",
				TestEntity.IVRLoginKeystoke.toString());
		RWproperties.writeProperties(path, "IVRVideoDemandKeystoke",
				TestEntity.IVRVideoDemandKeystoke.toString());
		RWproperties.writeProperties(path, "IVRPlayKeystoke",
				TestEntity.IVRPlayKeystoke.toString());
		RWproperties.writeProperties(path, "IVRVideoDemandAmount",
				TestEntity.IVRVideoDemandAmount.toString());
		RWproperties.writeProperties(path, "IVRLoginAmount",
				TestEntity.IVRLoginAmount.toString());
		RWproperties.writeProperties(path, "PhoneClientIP",
				TestEntity.phoneClientIP.toString());
		RWproperties.writeProperties(path, "PhoneClientPort",
				TestEntity.phoneClientPort.toString());
		RWproperties.writeProperties(path, "PhoneClientStartPhoneNumber",
				TestEntity.phoneClientStartPhoneNumber.toString());
		RWproperties.writeProperties(path, "PhoneClientStopPhoneNumber",
				TestEntity.phoneClientStopPhoneNumber.toString());
		RWproperties.writeProperties(path, "PhoneClientPhoneNumberStep",
				TestEntity.phoneClientPhoneNumberStep.toString());
		RWproperties.writeProperties(path, "PhoneClientStartCANumber",
				TestEntity.phoneClientStartCANumber.toString());
		RWproperties.writeProperties(path, "PhoneClientStopCANumber",
				TestEntity.phoneClientStopCANumber.toString());
		RWproperties.writeProperties(path, "PhoneClientCAStep",
				TestEntity.phoneClientCAStep.toString());

		RWproperties.writeProperties(path, "offeringID",
				TestEntity.offeringID.toString());

		RWproperties.writeProperties(path, "offeringID",
				TestEntity.offeringID.toString());
		RWproperties.writeProperties(path, "PhoneClientLoginAmount",
				TestEntity.phoneClientLoginAmount.toString());
		RWproperties.writeProperties(path, "PhoneClientPlayKeystoke",
				TestEntity.phoneClientPlayKeystoke.toString());
		RWproperties.writeProperties(path, "PhoneClientVideoDemandAmount",
				TestEntity.phoneClientVideoDemandAmount.toString());
		RWproperties.writeProperties(path, "PhoneRemoteIP",
				TestEntity.PhoneRemoteIP.toString());
		RWproperties.writeProperties(path, "PhoneRemotePort",
				TestEntity.PhoneRemotePort.toString());
		RWproperties.writeProperties(path, "PhoneRemoteStartPhoneNumber",
				TestEntity.PhoneRemoteStartPhoneNumber.toString());
		RWproperties.writeProperties(path, "PhoneRemoteStopPhoneNumber",
				TestEntity.PhoneRemoteStopPhoneNumber.toString());
		RWproperties.writeProperties(path, "PhoneRemotePhoneNumberStep",
				TestEntity.PhoneRemotePhoneNumberStep.toString());
		RWproperties.writeProperties(path, "PhoneRemoteStartCANumber",
				TestEntity.PhoneRemoteStartCANumber.toString());
		RWproperties.writeProperties(path, "PhoneRemoteStopCANumber",
				TestEntity.PhoneRemoteStopCANumber.toString());
		RWproperties.writeProperties(path, "PhoneRemoteCAStep",
				TestEntity.PhoneRemoteCAStep.toString());
		RWproperties.writeProperties(path, "PhoneRemoteLoginKeystoke",
				TestEntity.PhoneRemoteLoginKeystoke.toString());
		RWproperties.writeProperties(path, "PhoneRemoteVideoDemandKeystoke",
				TestEntity.PhoneRemoteVideoDemandKeystoke.toString());
		RWproperties.writeProperties(path, "PhoneRemotePlayKeystoke",
				TestEntity.PhoneRemotePlayKeystoke.toString());
		RWproperties.writeProperties(path, "PhoneRemoteVideoDemandAmount",
				TestEntity.PhoneRemoteVideoDemandAmount.toString());
		RWproperties.writeProperties(path, "PhoneRemoteLoginAmount",
				TestEntity.PhoneRemoteLoginAmount.toString());
		RWproperties.writeProperties(path, "Timeout",
				TestEntity.Timeout.toString());
		RWproperties.writeProperties(path, "AllTestTime",
				TestEntity.AllTestTime.toString());

		RWproperties.writeProperties(path, "isIVRLogin",
				TestEntity.isIVRLogin.toString());
		RWproperties.writeProperties(path, "isIVRVideoDemand",
				TestEntity.isIVRVideoDemand.toString());
		RWproperties.writeProperties(path, "isPhoneRemoteLogin",
				TestEntity.isPhoneRemoteLogin.toString());
		RWproperties.writeProperties(path, "isPhoneRemoteVideoDemand",
				TestEntity.isPhoneRemoteVideoDemand.toString());
		RWproperties.writeProperties(path, "isPhoneClientLogin",
				TestEntity.isPhoneClientLogin.toString());
		RWproperties.writeProperties(path, "isPhoneClientVideoDemand",
				TestEntity.isPhoneClientVideoDemand.toString());

		RWproperties
				.writeProperties(path, "isIVR", TestEntity.isIVR.toString());
		RWproperties.writeProperties(path, "isPR", TestEntity.isPR.toString());
		RWproperties.writeProperties(path, "isPC", TestEntity.isPC.toString());

	}

}
