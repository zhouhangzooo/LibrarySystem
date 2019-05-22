package com.book.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.entity.Student;
import com.book.service.ServiceFactory;
import com.book.util.StringUtils;

import cn.hutool.json.JSONObject;

public class StudentRegisterServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String s_age= request.getParameter("age");
		String sex = request.getParameter("sex");
		String grade = request.getParameter("grade");
		String profession = request.getParameter("profession");
		JSONObject json = new JSONObject();
		if (StringUtils.validateEmpty(id) && StringUtils.validateEmpty(password) && StringUtils.validateEmpty(name)) {
			Student stu = new Student();
			
			if(StringUtils.validateEmpty(s_age))
			{
				Integer age = Integer.parseInt(s_age);
				stu.setAge(age);
			}
			stu.setGrade(grade);
			stu.setId(id);
			stu.setName(name);
			stu.setPassword(password);
			stu.setSex(sex);
			stu.setProfession(profession);
			ServiceFactory.getStudentServiceInstance().insert(stu);
			
			json.put("code", "000000");
			json.put("message", "注册成功");
			response.getWriter().println(json);
		} else {
			json.put("code", "111111");
			json.put("message", "请输入学生ID号、姓名和密码！");
			response.getWriter().println(json);
		}
	}

}
