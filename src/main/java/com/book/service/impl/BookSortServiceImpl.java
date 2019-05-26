package com.book.service.impl;

import java.util.List;

import com.book.dao.DaoFactory;
import com.book.entity.BookSort;
import com.book.service.IBookSortService;

public class BookSortServiceImpl implements IBookSortService {

	public BookSort selectById(int id) {
		return DaoFactory.getIBookSortDaoInstance().selectById(id);
	}

	public BookSort selectByName(String name) {
		return DaoFactory.getIBookSortDaoInstance().selectByName(name);
	}

	public int insert(BookSort book) {
		return DaoFactory.getIBookSortDaoInstance().insert(book);
	}

	public int update(BookSort book) {
		return DaoFactory.getIBookSortDaoInstance().update(book);
	}

	public List<BookSort> selectList() {
		return DaoFactory.getIBookSortDaoInstance().selectList();
	}

	public int deleteById(int id) {
		return DaoFactory.getIBookSortDaoInstance().deleteById(id);
	}

}
