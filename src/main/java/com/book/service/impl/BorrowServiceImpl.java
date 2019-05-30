package com.book.service.impl;

import java.util.List;

import com.book.dao.DaoFactory;
import com.book.entity.Borrow;
import com.book.service.IBorrowService;

public class BorrowServiceImpl implements IBorrowService {

	public Borrow selectByISBN(String ISBN) {
		return DaoFactory.getIBorrowDaoInstance().selectByISBN(ISBN);
	}

	public List<Borrow> selectByID(String id) {
		return DaoFactory.getIBorrowDaoInstance().selectByID(id);
	}

	public int insert(Borrow borrow) {
		return DaoFactory.getIBorrowDaoInstance().insert(borrow);
	}

	public int update(Borrow borrow, String ISBN) {
		return DaoFactory.getIBorrowDaoInstance().update(borrow, ISBN);
	}

	public List<Borrow> selectList() {
		return DaoFactory.getIBorrowDaoInstance().selectList();
	}

	public int deleteByISBN(String ISBN) {
		return DaoFactory.getIBorrowDaoInstance().deleteByISBN(ISBN);
	}

	public int update_returnbook(Borrow borrow) {
		return DaoFactory.getIBorrowDaoInstance().update_returnbook(borrow);
	}

}
