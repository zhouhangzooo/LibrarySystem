package com.book.service;

import java.util.List;

import com.book.entity.Book;

/**
 * 业务逻辑层
 * 
 * @author ZhouHang
 * @date 2019/05/14
 */
public interface IBookService {

	public Book selectByISBN(String ISBN);

	public List<Book> selectByName(String name);

	public int insert(Book book);

	public int update(Book book);

	public List<Book> selectList();

	public int deleteByISBN(String ISBN);

	public boolean updateBookStatus(String ISBN, String s_id, String borrow_date, String expect_return_date);

}
