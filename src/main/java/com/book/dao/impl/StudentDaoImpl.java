package com.book.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.dao.BaseDao;
import com.book.dao.IStudentDao;
import com.book.entity.Student;

public class StudentDaoImpl extends BaseDao implements IStudentDao {

	public boolean login(String id, String password) {
		String sql = "select `s_id`, `password` from student where `s_id` = ? and `password` = ?";
		Object[] obj = { id, password };
		ResultSet rs = selectJDBC(sql, obj);
		try {
			if (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Student selectById(String id) {
		Student stu = new Student();
		String sql = "select * from student where s_id = ?";
		Object[] obj = { id };
		ResultSet rs = selectJDBC(sql, obj);
		try {
			while (rs.next()) {
				stu.setAge(rs.getInt("age"));
				stu.setCumulative(rs.getInt("cumulative"));
				stu.setGrade(rs.getString("grade"));
				stu.setId(rs.getString("s_id"));
				stu.setIntegrity(rs.getInt("integrity"));
				stu.setName(rs.getString("name"));
				stu.setPassword(rs.getString("password"));
				stu.setProfession(rs.getString("profession"));
				stu.setSex(rs.getString("sex"));
			}
			return stu;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean checkIsExist(String id) {
		String sql = "select s_id from student where s_id = ?";
		Object[] obj = { id };
		ResultSet rs = selectJDBC(sql, obj);
		try {
			if(rs.next()){
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public int insert(Student stu) {
		boolean result = checkIsExist(stu.getId());
		if(result)
		{
			return -1;
		}
		String sql = "insert into student (s_id, name, password, age, profession, grade, sex) values (?,?,?,?,?,?,?)";
		Object[] obj = { stu.getId(), stu.getName(), stu.getPassword(), stu.getAge(), stu.getProfession(),
				stu.getGrade(), stu.getSex() };
		int lines = updateJDBC(sql, obj);
		if (lines > 0) {
			return 1;
		}
		return 0;
	}

	public int update(Student stu) {
		String sql = "update student set s_id = ?, name = ?, password = ?, age = ?, profession = ?, grade = ?, sex = ?";
		Object[] obj = { stu.getId(), stu.getName(), stu.getPassword(), stu.getAge(), stu.getProfession(),
				stu.getGrade(), stu.getSex() };
		int lines = updateJDBC(sql, obj);
		if (lines > 0) {
			return 1;
		}
		return 0;
	}

	public List<Student> selectList() {
		List<Student> lists = new ArrayList<Student>();
		String sql = "select * from student";
		Object[] obj = {};
		ResultSet rs = selectJDBC(sql, obj);
		try {
			while (rs.next()) {

				Student stu = new Student();
				stu.setAge(rs.getInt("age"));
				stu.setCumulative(rs.getInt("cumulative"));
				stu.setGrade(rs.getString("grade"));
				stu.setId(rs.getString("s_id"));
				stu.setIntegrity(rs.getInt("integrity"));
				stu.setName(rs.getString("name"));
				stu.setPassword(rs.getString("password"));
				stu.setProfession(rs.getString("profession"));
				stu.setSex(rs.getString("sex"));

				lists.add(stu);
			}
			return lists;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int deleteById(String id) {
		String sql = "delete from student where s_id = ?";
		Object[] obj = { id };
		int lines = updateJDBC(sql, obj);
		if (lines > 0) {
			return 1;
		}
		return 0;
	}

}
