package com.book.dao;

import com.book.dao.impl.BookDaoImpl;
import com.book.dao.impl.BorrowDaoImpl;
import com.book.dao.impl.ManagerDaoImpl;
import com.book.dao.impl.StudentDaoImpl;

public class DaoFactory {

	private static IStudentDao m_stu;
	private static IBorrowDao m_borrow;
	private static IBookDao m_book;
	private static IManagerDao m_manager;

	public static IStudentDao getIStudentDaoInstance() {
		if (m_stu == null) {
			m_stu = new StudentDaoImpl();
		}
		return m_stu;
	}

	public static IBorrowDao getIBorrowDaoInstance() {
		if (m_borrow == null) {
			m_borrow = new BorrowDaoImpl();
		}
		return m_borrow;
	}

	public static IBookDao getIBookDaoInstance() {
		if (m_book == null) {
			m_book = new BookDaoImpl();
		}
		return m_book;
	}

	public static IManagerDao getIManagerDaoInstance() {
		if (m_manager == null) {
			m_manager = new ManagerDaoImpl();
		}
		return m_manager;
	}
}
