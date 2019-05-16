package com.book.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Borrow {

	private int id;
	private String ISBN;
	private String s_id;
	private BigDecimal price;
	private String book_name;
	private Date borrow_date;
	private Date expect_return_date;
	private Date return_date;

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

	public Date getBorrow_date() {
		return borrow_date;
	}

	public void setBorrow_date(Date borrow_date) {
		this.borrow_date = borrow_date;
	}

	public Date getExpect_return_date() {
		return expect_return_date;
	}

	public void setExpect_return_date(Date expect_return_date) {
		this.expect_return_date = expect_return_date;
	}

	public Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}

}
