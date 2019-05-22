package com.book.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Borrow {

	private int id;
	private String ISBN;
	private String s_id;
	private BigDecimal price;
	private String book_name;
	private String borrow_date;
	private String expect_return_date;
	private String return_date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getS_id() {
		return s_id;
	}

	public void setS_id(String s_id) {
		this.s_id = s_id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBorrow_date() {
		return borrow_date;
	}

	public void setBorrow_date(String borrow_date) {
		this.borrow_date = borrow_date;
	}

	public String getExpect_return_date() {
		return expect_return_date;
	}

	public void setExpect_return_date(String expect_return_date) {
		this.expect_return_date = expect_return_date;
	}

	public String getReturn_date() {
		return return_date;
	}

	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}

}
