package com.book.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.dao.BaseDao;
import com.book.dao.IBorrowDao;
import com.book.entity.Borrow;

public class BorrowDaoImpl extends BaseDao implements IBorrowDao {

	public Borrow selectByISBN(String ISBN) {
		Borrow m = new Borrow();
		String sql = "select * from borrow where ISBN = ?";
		Object[] obj = { ISBN };
		ResultSet rs = selectJDBC(sql, obj);
		try {
			while (rs.next()) {
				m.setId(rs.getInt("id"));
				m.setISBN(rs.getString("iSBN"));
				m.setBook_name(rs.getString("book_name"));
				m.setBorrow_date(rs.getDate("borrow_date"));
				m.setExpect_return_date(rs.getDate("expect_return_date"));
				m.setPrice(rs.getBigDecimal("book_price"));
				m.setReturn_date(rs.getDate("return_date"));
				m.setS_id(rs.getString("s_id"));
			}
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Borrow> selectByID(String id) {
		List<Borrow> lists = new ArrayList<Borrow>();
		String sql = "select * from borrow where s_id = ?";
		Object[] obj = { id };
		ResultSet rs = selectJDBC(sql, obj);
		try {
			while (rs.next()) {
				Borrow m = new Borrow();
				m.setId(rs.getInt("id"));
				m.setISBN(rs.getString("iSBN"));
				m.setBook_name(rs.getString("book_name"));
				m.setBorrow_date(rs.getDate("borrow_date"));
				m.setExpect_return_date(rs.getDate("expect_return_date"));
				m.setPrice(rs.getBigDecimal("book_price"));
				m.setReturn_date(rs.getDate("return_date"));
				m.setS_id(rs.getString("s_id"));

				lists.add(m);
			}
			return lists;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int insert(Borrow m) {
		String sql = "insert into borrow (ISBN, s_id, price, book_name, borrow_date, expect_return_date, return_date) values (?,?,?,?,?,?,?)";
		Object[] obj = { m.getISBN(), m.getS_id(), m.getPrice(), m.getBook_name(), m.getBorrow_date(),
				m.getExpect_return_date(), m.getReturn_date() };
		int lines = updateJDBC(sql, obj);
		if (lines > 0) {
			return 1;
		}
		return 0;
	}

	public int update(Borrow m) {
		String sql = "update borrow set ISBN = ?, s_id = ?, price = ?, book_name = ?, borrow_date = ?, expect_return_date = ?, return_date = ?";
		Object[] obj = { m.getISBN(), m.getS_id(), m.getPrice(), m.getBook_name(), m.getBorrow_date(),
				m.getExpect_return_date(), m.getReturn_date() };
		int lines = updateJDBC(sql, obj);
		if (lines > 0) {
			return 1;
		}
		return 0;
	}

	public List<Borrow> selectList() {
		List<Borrow> lists = new ArrayList<Borrow>();
		String sql = "select * from borrow";
		Object[] obj = {};
		ResultSet rs = selectJDBC(sql, obj);
		try {
			while (rs.next()) {

				Borrow m = new Borrow();
				m.setId(rs.getInt("id"));
				m.setISBN(rs.getString("iSBN"));
				m.setBook_name(rs.getString("book_name"));
				m.setBorrow_date(rs.getDate("borrow_date"));
				m.setExpect_return_date(rs.getDate("expect_return_date"));
				m.setPrice(rs.getBigDecimal("book_price"));
				m.setReturn_date(rs.getDate("return_date"));
				m.setS_id(rs.getString("s_id"));

				lists.add(m);
			}
			return lists;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int deleteByISBN(String ISBN) {
		String sql = "delete from borrow where ISBN = ?";
		Object[] obj = { ISBN };
		int lines = updateJDBC(sql, obj);
		if (lines > 0) {
			return 1;
		}
		return 0;
	}

}
