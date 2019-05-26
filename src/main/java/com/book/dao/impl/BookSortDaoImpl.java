package com.book.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.dao.BaseDao;
import com.book.dao.IBookSortDao;
import com.book.dao.IBorrowDao;
import com.book.entity.Book;
import com.book.entity.BookSort;
import com.book.entity.Borrow;
import com.book.entity.Student;

public class BookSortDaoImpl extends BaseDao implements IBookSortDao {

	public BookSort selectById(int id) {
		BookSort m = new BookSort();
		String sql = "select * from book_sort where sort_id = ?";
		Object[] obj = { id };
		ResultSet rs = selectJDBC(sql, obj);
		try {
			while (rs.next()) {
				m.setId(rs.getInt("sort_id"));
				m.setSort_name(rs.getString("sort_name"));
			}
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public BookSort selectByName(String name) {
		BookSort m = new BookSort();
		String sql = "select * from book_sort where sort_name = ?";
		Object[] obj = { name };
		ResultSet rs = selectJDBC(sql, obj);
		try {
			while (rs.next()) {
				m.setId(rs.getInt("sort_id"));
				m.setSort_name(rs.getString("sort_name"));
			}
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean checkIsExist(String name) {
		String sql = "select sort_name from book_sort where sort_name = ?";
		Object[] obj = { name };
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
		}
	}

	public int insert(BookSort sort) {
		boolean result = checkIsExist(sort.getSort_name());
		if (result) {
			return -1;
		}
		String sql = "insert into book_sort (sort_name) values (?)";
		Object[] obj = { sort.getSort_name() };
		int lines = updateJDBC(sql, obj);
		if (lines > 0) {
			return 1;
		}
		return 0;
	}

	public int update(BookSort sort) {
		String sql = "update book_sort set sort_name = ? where sort_id = ? ";
		Object[] obj = { sort.getSort_name(), sort.getId() };
		int lines = updateJDBC(sql, obj);
		if (lines > 0) {
			return 1;
		}
		return 0;
	}

	public List<BookSort> selectList() {
		List<BookSort> lists = new ArrayList<BookSort>();
		String sql = "select * from book_sort";
		Object[] obj = {};
		ResultSet rs = selectJDBC(sql, obj);
		try {
			while (rs.next()) {

				BookSort stu = new BookSort();
				stu.setId(rs.getInt("sort_id"));
				stu.setSort_name(rs.getString("sort_name"));

				lists.add(stu);
			}
			return lists;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int deleteById(int id) {
		String sql = "delete from book_sort where sort_id = ?";
		Object[] obj = { id };
		int lines = updateJDBC(sql, obj);
		if (lines > 0) {
			return 1;
		}
		return 0;
	}

}
