package com.readinglife.tools.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * 是否是当月最后一天  返回0
     * @param date
     * @return
     */
    public static int isLastDayOfMonth(Date date){
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.add(Calendar.MONTH,1);
    	c.set(Calendar.DATE, 1);
    	c.add(Calendar.DATE, -1);
    	Date last = c.getTime();
    	return last.compareTo(date);
    }
    
    /**
     * 是否是当月第一天 返回 0
     * @param date
     * @return
     */
    public static int isFirstDayOfMonth(Date date){
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.set(Calendar.DATE, 1);
    	Date first = c.getTime();
    	return first.compareTo(date);
    }

    /**
     * 是否是当年最后一天  返回0
     * @param date
     * @return
     */
    public static int isLastDayOfYear(Date date){
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.add(Calendar.YEAR,1);
    	c.set(Calendar.MONTH, 1);
    	c.set(Calendar.DATE, 1);
    	c.add(Calendar.DATE, -1);
    	Date last = c.getTime();
    	return last.compareTo(date);
    }
    
    /**
     * 是否是当年第一天 返回 0
     * @param date
     * @return
     */
    public static int isFirstDayOfYear(Date date){
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	c.set(Calendar.MONTH, 1);
    	c.set(Calendar.DATE, 1);
    	Date first = c.getTime();
    	return first.compareTo(date);
    }
    
    /**
     * 购买媒体资源时结束日期计算
     * @param startTime
     * @param mediaPutnum
     * @param makeofferType
     * @return
     */
    public static Date getEndTime(Date startTime,Integer mediaPutnum,Integer makeofferType){
		Calendar c = Calendar.getInstance();
		c.setTime(startTime);
		switch (makeofferType){
			case 4:
				c.add(Calendar.DAY_OF_MONTH, mediaPutnum-1);
				break;
			case 3:
				c.add(Calendar.WEEK_OF_MONTH, mediaPutnum);
				c.add(Calendar.DAY_OF_MONTH, -1);
				break;
			case 2:
				if(DateUtil.isFirstDayOfMonth(c.getTime())==0){
					c.add(Calendar.MONTH, mediaPutnum);
				}else{
					c.add(Calendar.DAY_OF_MONTH, mediaPutnum*30-1);
				}
				break;
			case 1:
				if(DateUtil.isFirstDayOfYear(c.getTime())==0){
					c.add(Calendar.YEAR, mediaPutnum);
					c.add(Calendar.DAY_OF_MONTH, -1);
				}else{
					c.add(Calendar.DAY_OF_MONTH, mediaPutnum*365-1);
				}
				break;
		}
		return c.getTime();
	}
    
    /**
	 * 字符串转换到时间格式
	 * @param dateStr 需要转换的字符串
	 * @param formatStr 需要格式的目标字符串  举例 yyyy-MM-dd
	 * @return Date 返回转换后的时间
	 * @throws ParseException 转换异常
	 */
	public static Date StringToDate(String dateStr,String formatStr){
		DateFormat sdf=new SimpleDateFormat(formatStr);
		Date date=null;
		try {
			date = sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
}
