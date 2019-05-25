package com.book.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Book {

	private String ISBN;
	private String book_name;
	private String book_author;
	private String book_pub;
	private int book_borrow;
	private int sort_id;
	private String book_record;
	private BigDecimal book_price;
	private String sort_name;

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_author() {
		return book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	public String getBook_pub() {
		return book_pub;
	}

	public void setBook_pub(String book_pub) {
		this.book_pub = book_pub;
	}

	public int getBook_borrow() {
		return book_borrow;
	}

	public void setBook_borrow(int book_borrow) {
		this.book_borrow = book_borrow;
	}

	public int getSort_id() {
		return sort_id;
	}

	public void setSort_id(int sort_id) {
		this.sort_id = sort_id;
	}

	public String getBook_record() {
		return book_record;
	}

	public void setBook_record(String book_record) {
		this.book_record = book_record;
	}

	public BigDecimal getBook_price() {
		return book_price;
	}

	public void setBook_price(BigDecimal book_price) {
		this.book_price = book_price;
	}
	
	public String getSort_name() {
		return sort_name;
	}

	public void setSort_name(String sort_name) {
		this.sort_name = sort_name;
	}

}
