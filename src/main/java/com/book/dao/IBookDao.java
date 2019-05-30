package com.book.dao;

import java.util.List;

import com.book.entity.Book;

/**
 * 数据访问层(Dao)
 * 
 * @author ZhouHang
 * @date 2019/05/14
 */
public interface IBookDao {

	public Book selectByISBN(String ISBN);

	public List<Book> selectByName(String name);

	public int insert(Book book);

	public int update(Book book, String ISBN);

	public List<Book> selectList();

	public int deleteByISBN(String ISBN);

	public boolean updateBookStatus(String ISBN, String s_id, String borrow_date, String expect_return_date);

	public boolean returnBookStatus(String ISBN, String return_date);

}
