package com.book.dao;

import java.util.List;

import com.book.entity.Student;

/**
 * 数据访问层(Dao)
 * 
 * @author ZhouHang
 * @date 2019/05/14
 */
public interface IStudentDao {

	public boolean login(String id, String password);

	public Student selectById(String id);

	public int insert(Student stu);

	public int update(Student stu, String s_id);

	public List<Student> selectList();

	public int deleteById(String id);

}
