package com.book.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.dao.BaseDao;
import com.book.dao.DaoFactory;
import com.book.dao.IBookDao;
import com.book.entity.Book;
import com.book.util.DateUtils;
import com.mysql.cj.util.Util;

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
				m.setBook_borrow(rs.getInt("book_borrow"));
				m.setBook_price(rs.getBigDecimal("book_price"));
				m.setBook_pub(rs.getString("book_pub"));
				m.setBook_record(rs.getString("book_record"));
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
				m.setBook_borrow(rs.getInt("book_borrow"));
				m.setBook_price(rs.getBigDecimal("book_price"));
				m.setBook_pub(rs.getString("book_pub"));
				m.setBook_record(rs.getString("book_record"));
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
		String sql = "insert into book (ISBN, book_name, book_author, book_pub, book_borrow, sort_id, book_record, book_price) values (?,?,?,?,?,?,?,?)";
		Object[] obj = { m.getISBN(), m.getBook_name(), m.getBook_author(), m.getBook_pub(), m.getBook_borrow(),
				m.getSort_id(), m.getBook_record(), m.getBook_price() };
		int lines = updateJDBC(sql, obj);
		if (lines > 0) {
			return 1;
		}
		return 0;
	}

	public int update(Book m) {
		String sql = "update book set ISBN = ?, book_name = ?, book_author = ?, book_pub = ?, book_borrow = ?, sort_id = ?, book_record = ?, book_price = ?";
		Object[] obj = { m.getISBN(), m.getBook_name(), m.getBook_author(), m.getBook_pub(), m.getBook_borrow(),
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
				m.setISBN(rs.getString("ISBN"));
				m.setBook_author(rs.getString("book_author"));
				m.setBook_name(rs.getString("book_name"));
				m.setBook_borrow(rs.getInt("book_borrow"));
				m.setBook_price(rs.getBigDecimal("book_price"));
				m.setBook_pub(rs.getString("book_pub"));
				m.setBook_record(rs.getString("book_record"));
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

	//借阅操作，修改book的book_borrow的值，0为可借阅，1为已借阅，另外在借阅表添加借阅书籍的数据
	public boolean updateBookStatus(String ISBN) {
		Book m = new Book();
		String sql = "select * from book where ISBN = ?";
		Object[] obj = { ISBN };
		ResultSet rs = selectJDBC(sql, obj);
		try {
			while (rs.next()) {
				m.setISBN(rs.getString("iSBN"));
				m.setBook_author(rs.getString("book_author"));
				m.setBook_name(rs.getString("book_name"));
				if(rs.getInt("book_borrow")!= 0)
				{
					return false;
				}
				m.setBook_borrow(1);
				m.setBook_price(rs.getBigDecimal("book_price"));
				m.setBook_pub(rs.getString("book_pub"));
				m.setBook_record(rs.getString("book_record"));
				m.setSort_id(rs.getInt("sort_id"));
			}
			int result = update(m);
			if(result == 0)
			{
				return false;
			}
			//TODO 在借阅表中添加数据
//			DaoFactory.getIBorrowDaoInstance().insert(borrow);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
