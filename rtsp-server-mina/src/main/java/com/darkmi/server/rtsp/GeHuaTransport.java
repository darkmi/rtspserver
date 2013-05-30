package com.darkmi.server.rtsp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 歌华请求和响应的Transport.
 * Copyright (c) 永新视博.
 * @version 1.0  2012-01-09 mxh created.
 *           1.1  2012-01-10 mxh updated.
 */
public class GeHuaTransport {
	private static Logger logger = LoggerFactory.getLogger(GeHuaTransport.class);

	private TransportProtocol transportProtocol;
	private Profile profile;
	private LowerTransport lowerTransport;
	private DeliveryType deliveryType;
	private String client;
	private String bandwidth;
	private String qamName;
	private String modulation;

	private String destination;
	private int clientPort;
	private String edgeInputGroup;

	private String qamDestination;
	private String qamGroup;

	/**
	 * 默认构造函数.
	 */
	public GeHuaTransport() {
		transportProtocol = TransportProtocol.None;
		profile = Profile.None;
		lowerTransport = LowerTransport.None;
		deliveryType = DeliveryType.None;
		client = null;
		bandwidth = null;
		qamName = null;
		modulation = null;
		
		destination = null;
		clientPort = 0;
		edgeInputGroup = null;
		
		qamDestination = null;
		qamGroup = null;
	}

	/**
	 * 构造函数.<br>
	 * 用来构造从RCServer发往RM的RtspTransport.
	 * 如果vsType为NONE,则可判断RtspTransport为RCServer与RM所用.
	 */
	public GeHuaTransport(String transport) {
		transportProtocol = TransportProtocol.None;
		profile = Profile.None;
		lowerTransport = LowerTransport.None;
		deliveryType = DeliveryType.None;
		client = null;
		bandwidth = null;
		qamName = null;
		modulation = null;
		
		destination = null;
		clientPort = 0;
		edgeInputGroup = null;
		
		qamDestination = null;
		qamGroup = null;

		parseTransport(transport);
		//if (transport.compareToIgnoreCase(this.toString()) != 0) {
		//	logger.warn("RtspTransport转换错误.");
		//	logger.debug("转换之前的transport is --> " + transport);
		//	logger.debug("转换之后的transport is --> " + this.toString());
		//	throw new Exception("RtspTransport转换错误.");
		//}
	}

	/**
	 * 将字符串转换为RtspTransport对象.
	 */
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
			} else if (tok.startsWith("client")) {
				String[] temp = tok.split("=");
				if (temp.length > 0) {
					if (temp[0].equals("client")) {
						setClient(_getStrValue(tok));
					} else if (temp[0].equalsIgnoreCase("client_port")) {
						setClientPort(Integer.parseInt(_getStrValue(tok)));
					}
				}
			} else if (tok.startsWith("bandwidth")) {
				this.setBandWidth(_getStrValue(tok));
			} else if (tok.startsWith("destination")) {
				setDestination(_getStrValue(tok));
			} else if (tok.startsWith("qam_name")) {
				this.setQamName(_getStrValue(tok));
			} else if (tok.startsWith("modulation")) {
				setModulation(_getStrValue(tok));
			} else if (tok.startsWith("destination")) {
				this.setDestination(_getStrValue(tok));
			} else if (tok.startsWith("qam_group")) {
				this.setQamGroup(_getStrValue(tok));
			} else if (tok.startsWith("edge_input_group")) {
				this.setEdgeInputGroup(_getStrValue(tok));
			} else if (tok.startsWith("qam_destination")) {
				this.setQamDestination(_getStrValue(tok));
			}
		}
	}

	/**
	 * 将RtspTransport对象转换为字符串.
	 */
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
		if (client != null) {
			sb.append(";client=").append(client);
		}
		if (bandwidth != null) {
			sb.append(";bandwidth=").append(bandwidth);
		}
		if (qamDestination != null) {
			sb.append(";qam_destination=").append(qamDestination);
		}
		if (qamName != null) {
			sb.append(";qam_name=").append(qamName);
		}
		if (destination != null) {
			sb.append(";destination=").append(destination);
		}
		if (clientPort != 0) {
			sb.append(";client_port=").append(clientPort);
		}
		if (qamGroup != null) {
			sb.append(";qam_group=").append(qamGroup);
		}
		if (edgeInputGroup != null) {
			sb.append(";edge_input_group=").append(edgeInputGroup);
		}
		if (modulation != null) {
			sb.append(";modulation=").append(modulation);
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

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getModulation() {
		return modulation;
	}

	public void setModulation(String modulation) {
		this.modulation = modulation;
	}

	public String getBandWidth() {
		return bandwidth;
	}

	public void setBandWidth(String bandWidth) {
		this.bandwidth = bandWidth;
	}

	public String getQamDestination() {
		return qamDestination;
	}

	public void setQamDestination(String qamDestination) {
		this.qamDestination = qamDestination;
	}

	public int getClientPort() {
		return clientPort;
	}

	public void setClientPort(int clientPort) {
		this.clientPort = clientPort;
	}

	public String getQamName() {
		return qamName;
	}

	public void setQamName(String qamName) {
		this.qamName = qamName;
	}

	public String getQamGroup() {
		return qamGroup;
	}

	public void setQamGroup(String qamGroup) {
		this.qamGroup = qamGroup;
	}

	public String getEdgeInputGroup() {
		return edgeInputGroup;
	}

	public void setEdgeInputGroup(String edgeInputGroup) {
		this.edgeInputGroup = edgeInputGroup;
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
			GeHuaTransport t1 = null;
			String str1 = "MP2T/DVBC/QAM;unicast;client=00AF123456DE;bandwidth=2700000;qam_name=Chicago.SouthBend.5;modulation=qam64";
			t1 = new GeHuaTransport(str1);
			logger.debug(t1.toString());
			
			GeHuaTransport t2 = null;
			String str2 = "MP2T/DVBC/QAM;unicast;client=00AF123456DE;bandwidth=2700000;qam_name=Chicago.SouthBend.10;modulation=qam64";
			t2 = new GeHuaTransport(str2);
			logger.debug(t2.toString());
			
			GeHuaTransport t3 = null;
			String str3 = "MP2T/DVBC/QAM;unicast;client=00AF123456DE;bandwidth=2700000;qam_name=Chicago.SouthBend.15;modulation=qam64";
			t3 = new GeHuaTransport(str3);
			logger.debug(t3.toString());
			
			GeHuaTransport t4 = null;
			String str4 = "MP2T/DVBC/UDP;unicast;client=00AF123456DE;destination=192.26.13.1;client_port=4588;edge_input_group=Chicago-UR1/2/1";
			t4 = new GeHuaTransport(str4);
			logger.debug(t4.toString());

			GeHuaTransport t5 = null;
			String str5 = "MP2T/DVBC/QAM;unicast;client=00AF123456DE;qam_destination=550000000.15;qam_name=Chicago.SouthBend.5;qam_group=Chicago.SouthBend.QG001;modulation=qam64";
			t5 = new GeHuaTransport(str5);
			logger.debug(t5.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
}
