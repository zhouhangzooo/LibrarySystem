package com.book.service;

import java.util.List;

import com.book.entity.Manager;

/**
 * 业务逻辑层
 * 
 * @author ZhouHang
 * @date 2019/05/14
 */
public interface IManagerService {
	
	public boolean login(String id,String password);

	public Manager selectById(String id);

	public int insert(Manager u);

	public int update(Manager manager, String id);

	public List<Manager> selectList();

	public int deleteById(String id);

}
