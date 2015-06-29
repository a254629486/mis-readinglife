package com.readinglife.tools.string;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	
	public static boolean isNotNull(String str) {
		if(null!=str&&!"".equals(str)){
			return true;
		} 
		return false;
	}
	
	public static boolean isNotBlank(String str) {
		if(isNotNull(str)&&isNotNull(str.trim())){
			return true;
		} 
		return false;
	}
	
	public static boolean isNotNull(Object obj) {
		if(null!=obj&&isNotNull(obj.toString())){
			return true;
		} 
		return false;
	}
	
	/**
	 * 格式化日期
	 * @param source
	 * @param format yyyy-mm-dd
	 * @return
	 */
	public static  String dateFormatToString(String source,String format) {
		if(source==null||"".equals(source)) return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setLenient(false);
		return dateFormat.format(source);
	}
	
	/**
	 * 格式化日期
	 * @param source
	 * @param format yyyy-mm-dd
	 * @return
	 */
	public static  String dateFormatToString(Date source,String format) {
		if(source==null||"".equals(source)) return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setLenient(false);
		return dateFormat.format(source);
	}
	
}
