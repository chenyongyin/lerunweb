package com.lerun.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	// 数据库用户名和密码
	static String DBUSER = "sa";
	static String DBPWD = "wahym1314";
	// 连接sqlserver数据库驱动程序的写法
	static String CLASSFORNAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	public static Connection getConnection() {
		// 1、定义连接数据库路径
		// 2、连接数据库用户名和密码
		// 3、定义连接数据库驱动程序

		// 定义URL，数据库访问的地址
		String DBURL = "jdbc:sqlserver://192.168.0.19:1433;DatabaseName=test";

		Connection conn = null;

		try {
			// 1 加载驱动，同时向驱动管理器报到
			Class.forName(CLASSFORNAME);
			//	  
			// 2，DriverManager负责建立数据库连接，
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPWD);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return conn;

	}


	// 关闭数据库连接
	public void closeAll(ResultSet rs, Statement st, Connection conn)
			throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public static void main(String args[]) {
		System.out.println(DBConnection.getConnection());

	}

}
