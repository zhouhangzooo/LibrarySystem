package com.book.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.dao.BaseDao;
import com.book.dao.IManagerDao;
import com.book.entity.Manager;

public class ManagerDaoImpl extends BaseDao implements IManagerDao {

	public boolean login(String id, String password) {
		String sql = "select `id`, `password` from manager where `id` = ? and `password` = ?";
		Object[] obj = { id, password };
		ResultSet rs = selectJDBC(sql, obj);
		try {
			if (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeJDBC();
		}
	}

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
		} finally {
			closeJDBC();
		}
	}

	public boolean checkIsExist(String id) {
		String sql = "select id from manager where id = ?";
		Object[] obj = { id };
		ResultSet rs = selectJDBC(sql, obj);
		try {
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeJDBC();
		}
	}

	public int insert(Manager m) {
		boolean result = checkIsExist(m.getId());
		if (result) {
			return -1;
		}
		String sql = "insert into manager (id, name, password, age, phone) values (?,?,?,?,?)";
		Object[] obj = { m.getId(), m.getName(), m.getPassword(), m.getAge(), m.getPhone() };
		int lines = updateJDBC(sql, obj);
		if (lines > 0) {
			return 1;
		}
		return 0;
	}

	public int update(Manager m, String id) {
		String sql = "update manager set id = ?, name = ?, password = ?, age = ?, phone = ? where id = ? ";
		Object[] obj = { m.getId(), m.getName(), m.getPassword(), m.getAge(), m.getPhone(), id };
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
		} finally {
			closeJDBC();
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
