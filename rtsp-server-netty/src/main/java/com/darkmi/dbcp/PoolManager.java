package com.darkmi.dbcp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;

@SuppressWarnings("rawtypes")
public class PoolManager {
	private static String driver = "com.mysql.jdbc.Driver",//
			url = "jdbc:mysql://192.168.14.207:3306/vodlite2?useUnicode=true&characterEncoding=utf-8",//URL    
			Name = "root",//
			Password = "123456";//

	private static Class driverClass = null;
	private static ObjectPool connectionPool = null;

	public PoolManager() {
	}

	private static void loadProperties() {
		try {
			java.io.InputStream stream = new java.io.FileInputStream("config.properties");
			java.util.Properties props = new java.util.Properties();
			props.load(stream);

			driver = props.getProperty("ORACLE_DRIVER");
			url = props.getProperty("ORACLE_URL");
			Name = props.getProperty("ORACLE_LOGIN_NAME");
			Password = props.getProperty("ORACLE_LOGIN_PASSWORD");

		} catch (FileNotFoundException e) {
			System.out.println("");
		} catch (IOException ie) {
			System.out.println("");
		}
	}

	private static synchronized void initDataSource() {
		if (driverClass == null) {
			try {
				driverClass = Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unused")
	public static void StartPool() {
		loadProperties();
		initDataSource();
		if (connectionPool != null) {
			ShutdownPool();
		}
		try {
			connectionPool = new GenericObjectPool(null);
			ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url, Name, Password);
			PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory,
					connectionPool, null, null, false, true);
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			driver.registerPool("dbpool", connectionPool);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**    
	 * �ͷ����ӳ�
	 */
	public static void ShutdownPool() {
		try {
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			driver.closePool("dbpool");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		if (connectionPool == null)
			StartPool();
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:dbpool");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static Connection getConnection(String name) {
		return getConnection();
	}

	public static void freeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void freeConnection(String name, Connection con) {
		freeConnection(con);
	}

	public static void main(String[] args) {
		try {
			Connection conn = PoolManager.getConnection();
			if (conn != null) {
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery("select * from tblgxinterface");
				int c = rs.getMetaData().getColumnCount();
				while (rs.next()) {
					System.out.println();
					for (int i = 1; i <= c; i++) {
						System.out.print(rs.getObject(i));
					}
				}
				rs.close();
			}
			PoolManager.freeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
