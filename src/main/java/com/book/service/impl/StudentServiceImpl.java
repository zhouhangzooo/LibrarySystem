package com.book.service.impl;

import java.util.List;

import com.book.dao.DaoFactory;
import com.book.entity.Student;
import com.book.service.IStudentService;

public class StudentServiceImpl implements IStudentService {

	public boolean login(Student student) {
		boolean result = DaoFactory.getIStudentDaoInstance().login(student);
		return result;
	}

	public Student selectById(String id) {
		Student stu = DaoFactory.getIStudentDaoInstance().selectById(id);
		return stu;
	}

	public int insert(Student stu) {
		int result = DaoFactory.getIStudentDaoInstance().insert(stu);
		return result;
	}

	public int update(Student stu) {
		int result = DaoFactory.getIStudentDaoInstance().update(stu);
		return result;
	}

	public List<Student> selectList() {
		List<Student> students = DaoFactory.getIStudentDaoInstance().selectList();
		return students;
	}

	public int deleteById(String id) {
		int result = DaoFactory.getIStudentDaoInstance().deleteById(id);
		return result;
	}

}
