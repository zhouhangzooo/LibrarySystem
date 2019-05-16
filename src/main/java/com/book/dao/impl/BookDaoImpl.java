package com.book.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.dao.BaseDao;
import com.book.dao.IBookDao;
import com.book.entity.Book;

public class BookDaoImpl extends BaseDao implements IBookDao {

	public Book selectByISBN(String ISBN) {
		Book m = new Book();
		String sql = "select * from book where ISBN = ?";
		Object[] obj = { ISBN };
		ResultSet rs = selectJDBC(sql, obj);
		try {
			while (rs.next()) {
				m.setISBN(rs.getString("iSBN"));
				m.setBook_author(rs.getString("book_author"));
				m.setBook_name(rs.getString("book_name"));
				m.setBook_num(rs.getInt("book_num"));
				m.setBook_price(rs.getBigDecimal("book_price"));
				m.setBook_pub(rs.getString("book_pub"));
				m.setBook_record(rs.getDate("book_record"));
				m.setSort_id(rs.getInt("sort_id"));
			}
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Book> selectByName(String name) {
		List<Book> lists = new ArrayList<Book>();
		String sql = "select * from book where book_name like %?%";
		Object[] obj = { name };
		ResultSet rs = selectJDBC(sql, obj);
		try {
			while (rs.next()) {
				Book m = new Book();
				m.setISBN(rs.getString("iSBN"));
				m.setBook_author(rs.getString("book_author"));
				m.setBook_name(rs.getString("book_name"));
				m.setBook_num(rs.getInt("book_num"));
				m.setBook_price(rs.getBigDecimal("book_price"));
				m.setBook_pub(rs.getString("book_pub"));
				m.setBook_record(rs.getDate("book_record"));
				m.setSort_id(rs.getInt("sort_id"));

				lists.add(m);
			}
			return lists;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int insert(Book m) {
		String sql = "insert into book (ISBN, book_name, book_author, book_pub, book_num, sort_id, book_record, book_price) values (?,?,?,?,?,?,?,?)";
		Object[] obj = { m.getISBN(), m.getBook_name(), m.getBook_author(), m.getBook_pub(), m.getBook_num(),
				m.getSort_id(), m.getBook_record(), m.getBook_price() };
		int lines = updateJDBC(sql, obj);
		if (lines > 0) {
			return 1;
		}
		return 0;
	}

	public int update(Book m) {
		String sql = "update book set ISBN = ?, book_name = ?, book_author = ?, book_pub = ?, book_num = ?, sort_id = ?, book_record = ?, book_price = ?";
		Object[] obj = { m.getISBN(), m.getBook_name(), m.getBook_author(), m.getBook_pub(), m.getBook_num(),
				m.getSort_id(), m.getBook_record(), m.getBook_price() };
		int lines = updateJDBC(sql, obj);
		if (lines > 0) {
			return 1;
		}
		return 0;
	}

	public List<Book> selectList() {
		List<Book> lists = new ArrayList<Book>();
		String sql = "select * from book";
		Object[] obj = {};
		ResultSet rs = selectJDBC(sql, obj);
		try {
			while (rs.next()) {

				Book m = new Book();
				m.setISBN(rs.getString("iSBN"));
				m.setBook_author(rs.getString("book_author"));
				m.setBook_name(rs.getString("book_name"));
				m.setBook_num(rs.getInt("book_num"));
				m.setBook_price(rs.getBigDecimal("book_price"));
				m.setBook_pub(rs.getString("book_pub"));
				m.setBook_record(rs.getDate("book_record"));
				m.setSort_id(rs.getInt("sort_id"));

				lists.add(m);
			}
			return lists;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int deleteByISBN(String ISBN) {
		String sql = "delete from book where ISBN = ?";
		Object[] obj = { ISBN };
		int lines = updateJDBC(sql, obj);
		if (lines > 0) {
			return 1;
		}
		return 0;
	}

}
