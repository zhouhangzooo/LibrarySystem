package com.book.servlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.entity.Manager;
import com.book.service.ServiceFactory;
import com.book.util.StringUtils;

import cn.hutool.json.JSONObject;

public class ManagerRegisterServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String s_age = request.getParameter("age");
		String phone = request.getParameter("phone");

		JSONObject json = new JSONObject();

		if (StringUtils.validateEmpty(id) && StringUtils.validateEmpty(password) && StringUtils.validateEmpty(name)
				&& StringUtils.validateEmpty(phone)) {
			Manager m = new Manager();

			if (StringUtils.validateEmpty(s_age)) {
				Integer age = Integer.parseInt(s_age);
				m.setAge(age);
			}
			m.setId(id);
			m.setName(name);
			m.setPassword(password);
			m.setPhone(phone);
			int result = ServiceFactory.getIManagerServiceInstance().insert(m);
			if (result == -1) {
				json.put("code", "222222");
				json.put("message", "用户已存在");
			} else if (result == 0) {
				json.put("code", "111111");
				json.put("message", "注册失败");
			} else {
				json.put("code", "000000");
				json.put("message", "注册成功");
			}
			response.getWriter().println(json);
		} else {
			json.put("code", "111111");
			json.put("message", "请输入工作号、姓名、密码和电话！");
			response.getWriter().println(json);
		}
	}

}
