package com.book.servlet.book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.service.ServiceFactory;
import com.book.util.StringUtils;

import cn.hutool.json.JSONObject;

/**
 * Servlet implementation class UpdateBookServlet
 */
public class UpdateBookServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ISBN = request.getParameter("ISBN");
		JSONObject json = new JSONObject();
		if(!StringUtils.validateEmpty(ISBN))
		{
			json.put("code", "111111");
			json.put("message", "请输入书籍编号");
			response.getWriter().println(json);
			return;
		}
		boolean result = ServiceFactory.getIBookServiceInstance().updateBookStatus(ISBN);
		if(result)
		{
			json.put("code", "000000");
			json.put("message", "借阅成功");
			response.getWriter().println(json);
		}else {
			json.put("code", "111111");
			json.put("message", "借阅失败");
			response.getWriter().println(json);
		}
	}

}