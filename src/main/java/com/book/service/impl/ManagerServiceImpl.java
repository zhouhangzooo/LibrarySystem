package com.book.service.impl;

import java.util.List;

import com.book.dao.DaoFactory;
import com.book.entity.Manager;
import com.book.service.IManagerService;

public class ManagerServiceImpl implements IManagerService {

	public boolean login(String id, String password) {
		boolean result = DaoFactory.getIManagerDaoInstance().login(id, password);
		return result;
	}

	public Manager selectById(String id) {
		Manager manager = DaoFactory.getIManagerDaoInstance().selectById(id);
		return manager;
	}

	public int insert(Manager u) {
		int result = DaoFactory.getIManagerDaoInstance().insert(u);
		return result;
	}

	public int update(Manager u) {
		int result = DaoFactory.getIManagerDaoInstance().update(u);
		return result;
	}

	public List<Manager> selectList() {
		List<Manager> managers = DaoFactory.getIManagerDaoInstance().selectList();
		return managers;
	}

	public int deleteById(String id) {
		int result = DaoFactory.getIManagerDaoInstance().deleteById(id);
		return result;
	}

}
