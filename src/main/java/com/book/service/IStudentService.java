package com.book.service;

import java.util.List;

import com.book.entity.Student;

/**
 * 业务逻辑层
 * 
 * @author ZhouHang
 * @date 2019/05/14
 */
public interface IStudentService {
	
	public boolean login(Student student);

	public Student selectById(String id);

	public int insert(Student stu);

	public int update(Student stu);

	public List<Student> selectList();

	public int deleteById(String id);

}
