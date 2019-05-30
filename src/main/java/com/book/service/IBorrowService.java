package com.book.service;

import java.util.List;

import com.book.entity.Borrow;

/**
 * 业务逻辑层
 * 
 * @author ZhouHang
 * @date 2019/05/14
 */
public interface IBorrowService {

	public Borrow selectByISBN(String ISBN);

	public List<Borrow> selectByID(String id);

	public int insert(Borrow borrow);

	public int update_returnbook(Borrow borrow);

	public int update(Borrow borrow, String ISBN);

	public List<Borrow> selectList();

	public int deleteByISBN(String ISBN);

}
