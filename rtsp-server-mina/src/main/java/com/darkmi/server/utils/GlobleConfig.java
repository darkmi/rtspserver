package com.darkmi.server.utils;

import java.util.Properties;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 获取xmlsend.properties中相关配置参数
 * Copyright (c) 永新视博
 * All Rights Reserved.
 * @version 1.0  2012-5-7 上午10:10:00 gaozhenzhai created
 */
public class GlobleConfig {
	static final String file = "xmlsend";
	static ResourceBundle rb = null;
	private static Logger log = LoggerFactory.getLogger(GlobleConfig.class);
	private static Properties sysData = new Properties();

	public static String getProperty(String key) {
		String result = null;
		try {
			if (sysData.get(key) == null) {
				try {
					result = rb.getString(key);
					sysData.put(key, result);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			} else {
				result = sysData.getProperty(key);
			}
		} catch (Exception e) {
			log.error("error", e);
		}
		return result;
	}

	public static void put(Object key, Object value) {
		sysData.put(key, value);
	}

	public static Object get(Object key) {
		return sysData.get(key);
	}

	public static void remove(Object key) {
		sysData.remove(key);
	}

	static {
		try {
			rb = ResourceBundle.getBundle("xmlsend");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
