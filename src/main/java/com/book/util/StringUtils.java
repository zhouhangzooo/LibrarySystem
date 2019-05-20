package com.book.util;

public class StringUtils {
	public static boolean validateEmpty(String s) {
		if(null == s || "".equals(s)) {
			return false;
		}
		return true;
	}
}
