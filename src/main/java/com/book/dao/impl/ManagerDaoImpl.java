package com.book.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.dao.BaseDao;
import com.book.dao.IManagerDao;
import com.book.entity.Manager;

public class ManagerDaoImpl extends BaseDao implements IManagerDao {

	public Manager selectById(String id) {
		Manager m = new Manager();
		String sql = "select * from manager where id = ?";
		Object[] obj = { id };
		ResultSet rs = selectJDBC(sql, obj);
		try {
			while (rs.next()) {
				m.setAge(rs.getInt("age"));
				m.setId(rs.getString("id"));
				m.setName(rs.getString("name"));
				m.setPassword(rs.getString("password"));
				m.setPhone(rs.getString("phone"));
			}
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int insert(Manager m) {
		String sql = "insert into manager (id, name, password, age, phone) values (?,?,?,?,?)";
		Object[] obj = { m.getId(), m.getName(), m.getPassword(), m.getAge(), m.getPhone() };
		int lines = updateJDBC(sql, obj);
		if (lines > 0) {
			return 1;
		}
		return 0;
	}

	public int update(Manager m) {
		String sql = "update manager set id = ?, name = ?, password = ?, age = ?, phone = ?";
		Object[] obj = { m.getId(), m.getName(), m.getPassword(), m.getAge(), m.getPhone() };
		int lines = updateJDBC(sql, obj);
		if (lines > 0) {
			return 1;
		}
		return 0;
	}

	public List<Manager> selectList() {
		List<Manager> lists = new ArrayList<Manager>();
		String sql = "select * from manager";
		Object[] obj = {};
		ResultSet rs = selectJDBC(sql, obj);
		try {
			while (rs.next()) {

				Manager m = new Manager();
				m.setAge(rs.getInt("age"));
				m.setId(rs.getString("id"));
				m.setName(rs.getString("name"));
				m.setPassword(rs.getString("password"));
				m.setPhone(rs.getString("phone"));

				lists.add(m);
			}
			return lists;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int deleteById(String id) {
		String sql = "delete from manager where id = ?";
		Object[] obj = { id };
		int lines = updateJDBC(sql, obj);
		if (lines > 0) {
			return 1;
		}
		return 0;
	}

}
