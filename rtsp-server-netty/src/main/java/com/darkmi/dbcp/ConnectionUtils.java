package com.darkmi.dbcp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;


public class ConnectionUtils {
	// һЩcommon-dbcp�ڲ������protocol
	private static final String POOL_DRIVER_KEY = "jdbc:apache:commons:dbcp:";
	private static final String POLLING_DRIVER = "org.apache.commons.dbcp.PoolingDriver";


	private static PoolingDriver getPoolDriver() throws ClassNotFoundException, SQLException {
		Class.forName(POLLING_DRIVER);
		return (PoolingDriver) DriverManager.getDriver(POOL_DRIVER_KEY);
	}

	/**
	 * �����������
	 * 
	 * @throws Exception
	 */
	public static void destory() throws Exception {
		PoolingDriver driver = getPoolDriver();
		String[] names = driver.getPoolNames();
		for (String name : names) {
			driver.getConnectionPool(name).close();
		}
	}

	/**
	 * �����ӳ��л�ȡ��ݿ�����
	 */
	@SuppressWarnings("rawtypes")
	public static Connection getConnection(TableMetaData table) throws Exception {
		String key = table.getConnectionKey();

		PoolingDriver driver = getPoolDriver();

		ObjectPool pool = null;
		// �����Ҳ������ӳػ����쳣, ��Ҫcatchһ��
		try {
			pool = driver.getConnectionPool(key);
		} catch (Exception e) {
		}

		if (pool == null) {
			// �����ݿ����͹������ӹ���
			ConnectionFactory connectionFactory = null;
			if (table.getDbAddr() != null && TableMetaData.DB_TYPE_MYSQL == table.getDbType()) {
				Class.forName(TableMetaData.MYSQL_DRIVER);
				connectionFactory = new DriverManagerConnectionFactory(table.getDBUrl(), null);
			} else {
				Class.forName(TableMetaData.ORACLE_DRIVER);
				connectionFactory = new DriverManagerConnectionFactory(table.getDBUrl(), table.getDbuser(), table.getDbpass());
			}

			// �������ӳ�
			ObjectPool connectionPool = new GenericObjectPool(null);
			new PoolableConnectionFactory(connectionFactory, connectionPool, null, null, false, true);

			// �����ӳ�ע�ᵽdriver��
			driver.registerPool(key, connectionPool);
		}

		// �����ӳ�����һ������
		return DriverManager.getConnection(POOL_DRIVER_KEY + key);
	}

}
