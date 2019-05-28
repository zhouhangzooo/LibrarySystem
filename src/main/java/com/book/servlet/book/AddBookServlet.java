package com.book.servlet.book;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.entity.Book;
import com.book.service.ServiceFactory;
import com.book.util.StringUtils;

import cn.hutool.json.JSONObject;

/**
 * Servlet implementation class BookServlet
 */
public class AddBookServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String book_ISBN = request.getParameter("book_ISBN");
		String book_name = request.getParameter("book_name");
		String book_author = request.getParameter("book_author");
		String book_pub = request.getParameter("book_pub");
		String book_record = request.getParameter("book_record");
		String book_price = request.getParameter("book_price");
		String book_sort = request.getParameter("book_sort");
		String book_borrow = request.getParameter("book_borrow");

		JSONObject json = new JSONObject();
		if (StringUtils.validateEmpty(book_ISBN) && StringUtils.validateEmpty(book_name)
				&& StringUtils.validateEmpty(book_name) && StringUtils.validateEmpty(book_price)) {

			Book b = new Book();

			if (StringUtils.validateEmpty(book_borrow)) {
				Integer borrow = Integer.parseInt(book_borrow);
				b.setBook_borrow(borrow);
			}
			if (StringUtils.validateEmpty(book_sort)) {
				Integer sort_id = Integer.parseInt(book_sort);
				b.setSort_id(sort_id);
			}

			b.setBook_name(book_name);
			b.setBook_author(book_author);
			b.setBook_pub(book_pub);
			b.setBook_record(book_record);
			b.setISBN(book_ISBN);

			BigDecimal price = new BigDecimal(book_price);
			// 设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
			price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
			b.setBook_price(price);

			int result = ServiceFactory.getIBookServiceInstance().insert(b);
			if(result == -1) {
				json.put("code", "222222");
				json.put("message", "书籍编号已存在");
			}
			else if (result == 0) {
				json.put("code", "111111");
				json.put("message", "添加图书失败");
			} else {
				json.put("code", "000000");
				json.put("message", "添加图书成功");
			}
			response.getWriter().println(json);

		} else {
			json.put("code", "111111");
			json.put("message", "缺少参数！");
			response.getWriter().println(json);
		}
	}

}
