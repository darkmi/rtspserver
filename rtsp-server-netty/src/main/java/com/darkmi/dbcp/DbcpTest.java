package com.darkmi.dbcp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.impl.GenericObjectPool;

public class DbcpTest {

	public static void main(String[] args) {
		GenericObjectPool connectionPool = new GenericObjectPool(null);
		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory("jdbc:mysql://localhost:3306/icdpub",
																				 "icdpub", 
																				 "icdpub");
		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory,
																							connectionPool, 
																							null, 
																							null, 
																							false, 
																							true);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		PoolingDriver driver = null;
		try {
			driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		driver.registerPool("example", connectionPool);

		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:example");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(conn);
		try {
			ResultSet rs = conn.createStatement().executeQuery("select * from events");
			int numcols = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				for (int i = 1; i <= numcols; i++) {
					System.out.print("\t" + rs.getString(i));
				}
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
