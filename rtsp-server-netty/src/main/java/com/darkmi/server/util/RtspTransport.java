package com.darkmi.server.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RtspTransport {
	private static Logger logger = LoggerFactory.getLogger(RtspTransport.class);
	private TransportProtocol transportProtocol;
	private Profile profile;
	private LowerTransport lowerTransport;
	private DeliveryType deliveryType;
	private String destination;
	private int port;

	//private String edgeInputGroup;
	//private String qamDestination;
	//private String qamGroup;
	//private String client;
	//private String bandwidth;
	//private String qamName;
	//private String modulation;

	/**
	 * 默认构造函数.
	 */
	public RtspTransport() {
		transportProtocol = TransportProtocol.None;
		profile = Profile.None;
		lowerTransport = LowerTransport.None;
		deliveryType = DeliveryType.None;
		destination = null;
		port = 0;
		//edgeInputGroup = null;
		//qamDestination = null;
		//qamGroup = null;
		//client = null;
		//bandwidth = null;
		//qamName = null;
		//modulation = null;
	}

	public RtspTransport(String transport) {
		transportProtocol = TransportProtocol.None;
		profile = Profile.None;
		lowerTransport = LowerTransport.None;
		deliveryType = DeliveryType.None;

		destination = null;
		port = 0;

		//edgeInputGroup = null;
		//qamDestination = null;
		//qamGroup = null;
		//client = null;
		//bandwidth = null;
		//qamName = null;
		//modulation = null;

		parseTransport(transport);
		if (transport.compareToIgnoreCase(this.toString()) != 0) {
			logger.warn("RtspTransport Parse Error!");
			logger.debug("old transport is --> " + transport);
			logger.debug("new transport is --> " + this.toString());
		}
	}

	private void parseTransport(String transport) {
		for (String tok : transport.split(";")) {
			tok = tok.trim();
			// First check for the transport protocol
			if (tok.startsWith("RDP") || tok.startsWith("RAW") || tok.startsWith("MP2T")) {
				String[] tpl = tok.split("/");
				transportProtocol = TransportProtocol.valueOf(tpl[0]);
				if (tpl.length > 1) {
					profile = Profile.valueOf(tpl[1]);
				}
				if (tpl.length > 2) {
					lowerTransport = LowerTransport.valueOf(tpl[2]);
				}
				continue;
			}

			if (tok.equalsIgnoreCase("unicast")) {
				deliveryType = DeliveryType.unicast;
			} else if (tok.equalsIgnoreCase("multicast")) {
				deliveryType = DeliveryType.multicast;
			} else if (tok.startsWith("destination")) {
				setDestination(_getStrValue(tok));
			} else if (tok.startsWith("port")) {
				setPort(Integer.parseInt(_getStrValue(tok)));
				//String[] temp = tok.split("=");
				//if (temp.length > 0) {
				//	if (temp[0].equals("client")) {
				//		setClient(_getStrValue(tok));
				//	} else if (temp[0].equalsIgnoreCase("client_port")) {
				//		
				//	}
				//}
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(transportProtocol);
		if (profile != Profile.None) {
			sb.append("/").append(profile);
			if (lowerTransport != LowerTransport.None)
				sb.append("/").append(lowerTransport);
		}
		if (deliveryType != DeliveryType.None) {
			sb.append(";").append(deliveryType);
		}
		if (destination != null) {
			sb.append(";destination=").append(destination);
		}
		if (port != 0) {
			sb.append(";port=").append(port);
		}
		return sb.toString();
	}

	/*~~~~~~~~~Setter And Getter~~~~~~~~~~~~~~~~*/

	public DeliveryType getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(DeliveryType deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LowerTransport getLowerTransport() {
		return lowerTransport;
	}

	public void setLowerTransport(LowerTransport lowerTransport) {
		this.lowerTransport = lowerTransport;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public TransportProtocol getTransportProtocol() {
		return transportProtocol;
	}

	public void setTransportProtocol(TransportProtocol transportProtocol) {
		this.transportProtocol = transportProtocol;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int clientPort) {
		this.port = clientPort;
	}

	/*~~~~~~~~~  some methods ~~~~~~~~~~~~~~~~*/

	private static String _getStrValue(String str) {
		String[] list = str.split("=");
		if (list.length != 2)
			return null;

		return list[1];
	}

	/*~~~~~~~~~ enum definition ~~~~~~~~~~~~~~~~*/

	public enum TransportProtocol {
		None, RDT, RAW, MP2T
	}

	public enum Profile {
		None, AVP, DVBC, RTP
	}

	public enum LowerTransport {
		None, TCP, UDP, QAM
	}

	public enum DeliveryType {
		None, unicast, multicast
	}

	/*~~~~~~~~~ main ~~~~~~~~~~~~~~~~*/

	public static void main(String[] args) {
		try {
			RtspTransport t1 = null;
			String str1 = "MP2T/AVP;unicast;destination=10.1.1.196;port=49414";
			t1 = new RtspTransport(str1);
			logger.debug(t1.toString());

			//			NGODTransport t2 = null;
			//			String str2 = "MP2T/DVBC/QAM;unicast;client=00AF123456DE;bandwidth=2700000;qam_name=Chicago.SouthBend.10;modulation=qam64";
			//			t2 = new NGODTransport(str2);
			//			logger.debug(t2.toString());
			//
			//			NGODTransport t3 = null;
			//			String str3 = "MP2T/DVBC/QAM;unicast;client=00AF123456DE;bandwidth=2700000;qam_name=Chicago.SouthBend.15;modulation=qam64";
			//			t3 = new NGODTransport(str3);
			//			logger.debug(t3.toString());
			//
			//			NGODTransport t4 = null;
			//			String str4 = "MP2T/DVBC/UDP;unicast;client=00AF123456DE;destination=192.26.13.1;client_port=4588;edge_input_group=Chicago-UR1/2/1";
			//			t4 = new NGODTransport(str4);
			//			logger.debug(t4.toString());
			//
			//			NGODTransport t5 = null;
			//			String str5 = "MP2T/DVBC/QAM;unicast;client=00AF123456DE;qam_destination=550000000.15;qam_name=Chicago.SouthBend.5;qam_group=Chicago.SouthBend.QG001;modulation=qam64";
			//			t5 = new NGODTransport(str5);
			//			logger.debug(t5.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
