package com.book.servlet.booksort;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.entity.Book;
import com.book.entity.BookSort;
import com.book.service.ServiceFactory;
import com.book.util.StringUtils;

import cn.hutool.json.JSONObject;

/**
 * Servlet implementation class BookServlet
 */
public class AddBookSortServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sort_name = request.getParameter("sort_name");

		JSONObject json = new JSONObject();
		if (StringUtils.validateEmpty(sort_name)) {

			BookSort b = new BookSort();
			b.setSort_name(sort_name);

			int result = ServiceFactory.getIBookSortServiceInstance().insert(b);
			if (result == -1) {
				json.put("code", "222222");
				json.put("message", "该分类名称已存在");
			} else if (result == 0) {
				json.put("code", "111111");
				json.put("message", "添加图书分类失败");
			} else {
				json.put("code", "000000");
				json.put("message", "添加图书分类成功");
			}
			response.getWriter().println(json);

		} else {
			json.put("code", "111111");
			json.put("message", "缺少参数！");
			response.getWriter().println(json);
		}
	}

}
