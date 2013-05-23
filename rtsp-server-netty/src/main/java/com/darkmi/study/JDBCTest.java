package com.darkmi.study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTest {
	private static final String url = "";
	private static final String username = "";
	private static final String pwd = "";

	public static void main(String[] args) {
		// 1. 注册驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		// 声明变量，使用，而后关闭
		Connection conn = null; //数据库连接
		Statement stmt = null; //数据库表达式
		ResultSet rs = null; //结果集

		try {
			//2. 获取数据库的连接
			conn = DriverManager.getConnection(
					"jdbc:mysql://67.20.120.174:3306/onezesm5_huoxingren?useUnicode=true&characterEncoding=GBK",
					"onezesm5_mixh", "sweet2dead");

			//3. 获取表达式
			stmt = conn.createStatement();

			//4. 执行SQL
			rs = stmt.executeQuery("select * from bbs_forum_activityapply");

			//5. 现实结果集里面的数据
			while (rs.next()) {
				//System.out.println("编号=" + rs.getInt(1));
				//System.out.println("姓名=" + rs.getString("username"));
				//System.out.println("密码=" + rs.getString("password"));
				//System.out.println("年龄=" + rs.getString("age"));
				System.out.println("---------------");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
