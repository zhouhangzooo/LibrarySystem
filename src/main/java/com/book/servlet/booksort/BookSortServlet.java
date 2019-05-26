package com.book.servlet.booksort;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.entity.Book;
import com.book.entity.BookSort;
import com.book.service.ServiceFactory;

import cn.hutool.json.JSONObject;

/**
 * Servlet implementation class BookServlet
 */
public class BookSortServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<BookSort> bookSorts = ServiceFactory.getIBookSortServiceInstance().selectList();
		JSONObject json = new JSONObject();
		json.put("data", bookSorts);
		json.put("code", "000000");
		json.put("message", "请求成功");
		response.getWriter().println(json);
	}

}
