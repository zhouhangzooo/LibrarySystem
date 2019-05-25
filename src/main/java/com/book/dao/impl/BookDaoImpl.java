package com.book.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.dao.BaseDao;
import com.book.dao.DaoFactory;
import com.book.dao.IBookDao;
import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.util.DateUtils;
import com.mysql.cj.util.Util;

import cn.hutool.core.lang.Console;

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
		String sql = "update book set book_name = ?, book_author = ?, book_pub = ?, book_borrow = ?, sort_id = ?, book_record = ?, book_price = ? where ISBN = ?";
		Object[] obj = { m.getBook_name(), m.getBook_author(), m.getBook_pub(), m.getBook_borrow(), m.getSort_id(),
				m.getBook_record(), m.getBook_price(), m.getISBN() };
		int lines = updateJDBC(sql, obj);
		if (lines > 0) {
			return 1;
		}
		return 0;
	}

	public List<Book> selectList() {
		List<Book> lists = new ArrayList<Book>();
		//String sql = "select * from book";
		//左外连接查询
		String sql = "SELECT * FROM book a LEFT OUTER JOIN book_sort b ON a.`sort_id` = b.`sort_id`";
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
				m.setSort_name(rs.getString("sort_name"));
				
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

	/**
	 * 借阅操作，修改book的book_borrow的值，0为可借阅，1为已借阅，另外在借阅表添加借阅书籍的数据
	 * 
	 * @param ISBN
	 *            书唯一编号
	 * @param s_id
	 *            借阅人编号
	 * @param borrow_date
	 *            借阅日期（一般为当天）
	 * @param expect_return_date
	 *            归还日期
	 * @return
	 */
	public boolean updateBookStatus(String ISBN, String s_id, String borrow_date, String expect_return_date) {
		Book m = new Book();
		String sql = "select * from book where ISBN = ?";
		Object[] obj = { ISBN };
		ResultSet rs = selectJDBC(sql, obj);
		try {
			while (rs.next()) {
				m.setISBN(rs.getString("iSBN"));
				m.setBook_author(rs.getString("book_author"));
				m.setBook_name(rs.getString("book_name"));
				if (rs.getInt("book_borrow") != 0) {
					return false;
				}
				m.setBook_borrow(1);
				m.setBook_price(rs.getBigDecimal("book_price"));
				m.setBook_pub(rs.getString("book_pub"));
				m.setBook_record(rs.getString("book_record"));
				m.setSort_id(rs.getInt("sort_id"));
			}
			int result = update(m);
			if (result == 0) {
				System.out.println("==图书更新状态失败");
				return false;
			}

			// 在借阅表中添加数据
			Borrow borrow = new Borrow();
			borrow.setBook_borrow(m.getBook_borrow());
			borrow.setBook_name(m.getBook_name());
			borrow.setBorrow_date(borrow_date);
			borrow.setExpect_return_date(expect_return_date); // 预计归还日期由用户设置
			borrow.setISBN(m.getISBN());
			borrow.setPrice(m.getBook_price());
			borrow.setReturn_date(null); // 当归还时才设置日期
			borrow.setS_id(s_id);

			result = DaoFactory.getIBorrowDaoInstance().insert(borrow);
			if (result == 0) {
				System.out.println("==借阅插入数据失败");
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
