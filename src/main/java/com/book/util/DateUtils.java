package com.book.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
	
    /* 
     * 将时间转换为时间戳
     */    
    public static String dateToStamp(String s) {
        String res = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_LONG);
        Date date;
		try {
			date = simpleDateFormat.parse(s);
			long ts = date.getTime();
	        res = String.valueOf(ts);
	        return res;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
        
    }
    
    /* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_LONG);
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
