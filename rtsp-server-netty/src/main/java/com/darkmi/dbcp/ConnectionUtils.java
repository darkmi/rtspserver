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

/**
 * 创建连接
 * 
 * @since 2009-1-22 下午02:58:35
 */
public class ConnectionUtils {
	// 一些common-dbcp内部定义的protocol
	private static final String POOL_DRIVER_KEY = "jdbc:apache:commons:dbcp:";
	private static final String POLLING_DRIVER = "org.apache.commons.dbcp.PoolingDriver";

	/**
	 * 取得池化驱动器
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static PoolingDriver getPoolDriver() throws ClassNotFoundException, SQLException {
		Class.forName(POLLING_DRIVER);
		return (PoolingDriver) DriverManager.getDriver(POOL_DRIVER_KEY);
	}

	/**
	 * 销毁所有连接
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
	 * 从连接池中获取数据库连接
	 */
	public static Connection getConnection(TableMetaData table) throws Exception {
		String key = table.getConnectionKey();

		PoolingDriver driver = getPoolDriver();

		ObjectPool pool = null;
		// 这里找不到连接池会抛异常, 需要catch一下
		try {
			pool = driver.getConnectionPool(key);
		} catch (Exception e) {
		}

		if (pool == null) {
			// 根据数据库类型构建连接工厂
			ConnectionFactory connectionFactory = null;
			if (table.getDbAddr() != null && TableMetaData.DB_TYPE_MYSQL == table.getDbType()) {
				Class.forName(TableMetaData.MYSQL_DRIVER);
				connectionFactory = new DriverManagerConnectionFactory(table.getDBUrl(), null);
			} else {
				Class.forName(TableMetaData.ORACLE_DRIVER);
				connectionFactory = new DriverManagerConnectionFactory(table.getDBUrl(), table.getDbuser(), table.getDbpass());
			}

			// 构造连接池
			ObjectPool connectionPool = new GenericObjectPool(null);
			new PoolableConnectionFactory(connectionFactory, connectionPool, null, null, false, true);

			// 将连接池注册到driver中
			driver.registerPool(key, connectionPool);
		}

		// 从连接池中拿一个连接
		return DriverManager.getConnection(POOL_DRIVER_KEY + key);
	}

}
