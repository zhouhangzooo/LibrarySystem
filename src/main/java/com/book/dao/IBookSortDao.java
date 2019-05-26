package com.book.dao;

import java.util.List;

import com.book.entity.BookSort;

public interface IBookSortDao {

	public BookSort selectById(int id);

	public BookSort selectByName(String name);

	public int insert(BookSort sort);

	public int update(BookSort sort);

	public List<BookSort> selectList();

	public int deleteById(int id);

}
