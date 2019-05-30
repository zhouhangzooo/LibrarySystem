package com.book.servlet.borrow;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.service.ServiceFactory;
import com.book.util.StringUtils;

import cn.hutool.json.JSONObject;

/**
 * 归还图书
 */
public class ReturnBookServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ISBN = request.getParameter("ISBN");
		String borrow_date = request.getParameter("return_date");
		JSONObject json = new JSONObject();
		if (!StringUtils.validateEmpty(ISBN)) {
			json.put("code", "111111");
			json.put("message", "请输入书籍编号");
			response.getWriter().println(json);
			return;
		}
		boolean result = ServiceFactory.getIBookServiceInstance().returnBookStatus(ISBN, borrow_date);
		if (result) {
			json.put("code", "000000");
			json.put("message", "归还成功");
			response.getWriter().println(json);
		} else {
			json.put("code", "111111");
			json.put("message", "归还失败");
			response.getWriter().println(json);
		}
	}

}
