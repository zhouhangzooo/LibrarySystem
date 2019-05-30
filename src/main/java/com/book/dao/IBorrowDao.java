package com.book.dao;

import java.util.List;

import com.book.entity.Borrow;

/**
 * 数据访问层(Dao)
 * 
 * @author ZhouHang
 * @date 2019/05/14
 */
public interface IBorrowDao {

	public Borrow selectByISBN(String ISBN);

	public List<Borrow> selectByID(String id);
	
	public int insert(Borrow borrow);
	
	public int update_returnbook(Borrow borrow);

	public int update(Borrow borrow, String ISBN);

	public List<Borrow> selectList();

	public int deleteByISBN(String ISBN);

}
