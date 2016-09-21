package com.smart.comm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	* 日期转换成字符串
	* @param date 
	* @return str
	*/
	public static String DateToStr(Date date) throws Exception{
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String str = format.format(date);
	   return str;
	} 

	/**
	* 字符串转换成日期
	* @param str
	* @return date
	 * @throws ParseException 
	*/
	public static Date StrToDate(String str) throws Exception {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Date date = format.parse(str);
	   return date;
	}
	public static String stringDatePlusOne(String str) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(format.parse(str));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date date=calendar.getTime();
		String newString=format.format(date);
		return newString;
	}
	public static void main(String[] args) {
		try {
			System.out.println(stringDatePlusOne("2016-06-28"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
