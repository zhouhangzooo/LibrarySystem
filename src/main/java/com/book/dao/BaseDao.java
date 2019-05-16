package com.book.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {

	public Connection conn;
	public PreparedStatement ps;
	public ResultSet rs;

	// 与数据库建立连接
	public boolean getConnection() {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/library_system?serverTimezone=UTC";
		String user = "root";
		String password = "123456";

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 所有查询方法的模板
	public ResultSet selectJDBC(String sql, Object[] obj) {
		getConnection();
		try {
			// 使用Preparedstatement对象预编译sql
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 所有增删改方法的模板
	public int updateJDBC(String sql, Object[] obj) {
		getConnection();
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}
			int lines = ps.executeUpdate();
			return lines;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	// 释放资源
	public boolean closeJDBC() {
		try {
			if (!rs.isClosed()) {
				rs.close();
			}
			if (!ps.isClosed()) {
				ps.close();
			}
			if (!conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
