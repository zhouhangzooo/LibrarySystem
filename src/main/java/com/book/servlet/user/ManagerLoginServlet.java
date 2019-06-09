package com.book.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.service.ServiceFactory;
import com.book.util.StringUtils;

import cn.hutool.json.JSONObject;

/**
 * Servlet implementation class ManagerServlet
 */
public class ManagerLoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		JSONObject json = new JSONObject();

		if (StringUtils.validateEmpty(id) && StringUtils.validateEmpty(password)) {
			if (ServiceFactory.getIManagerServiceInstance().login(id, password)) {
				JSONObject object = new JSONObject();
				object.put("id", id);
				json.put("data", object);
				json.put("code", "000000");
				json.put("message", "登录成功");
				request.getSession().setAttribute("isManager", "true");
				response.getWriter().println(json);
			} else {
				json.put("code", "111111");
				json.put("message", "工作号或密码错误！");
				response.getWriter().println(json);
			}
		} else {
			json.put("code", "111111");
			json.put("message", "请输入工作号或密码！");
			response.getWriter().println(json);
		}
	}

}
