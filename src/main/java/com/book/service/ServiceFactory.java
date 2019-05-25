package com.book.service;

import com.book.service.impl.BookServiceImpl;
import com.book.service.impl.BorrowServiceImpl;
import com.book.service.impl.ManagerServiceImpl;
import com.book.service.impl.StudentServiceImpl;

public class ServiceFactory {

	private static IStudentService m_stu;
	private static IBorrowService m_borrow;
	private static IBookService m_book;
	private static IManagerService m_manager;

	public static IStudentService getIStudentServiceInstance() {
		if (m_stu == null) {
			m_stu = new StudentServiceImpl();
		}
		return m_stu;
	}

	public static IBorrowService getIBorrowServiceInstance() {
		if (m_borrow == null) {
			m_borrow = new BorrowServiceImpl();
		}
		return m_borrow;
	}

	public static IBookService getIBookServiceInstance() {
		if (m_book == null) {
			m_book = new BookServiceImpl();
		}
		return m_book;
	}

	public static IManagerService getIManagerServiceInstance() {
		if (m_manager == null) {
			m_manager = new ManagerServiceImpl();
		}
		return m_manager;
	}
}
