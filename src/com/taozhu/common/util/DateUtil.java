package com.taozhu.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	private static SimpleDateFormat sf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 获取当前时间
	 * @return
	 */
	public static Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}
	
	/**
	 * 获取当天最小的时间
	 * @return
	 */
	public static Timestamp getIntradayMinTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return new Timestamp(calendar.getTime().getTime());
	}
	
	/**
	 * 获取当天最大的时间
	 * @return
	 */
	public static Timestamp getIntradayMaxTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return new Timestamp(calendar.getTime().getTime());
	}
	
	/**
	 * 获取一天中最小的时间
	 * @param millis
	 * @return
	 */
	public static Timestamp getDayMinTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return new Timestamp(calendar.getTime().getTime());
	}
	
	/**
	 * 获取一天中最小的时间
	 * @param millis
	 * @return
	 */
	public static Timestamp getDayMinTime(String dateStr) {
		Date date = parseDate(dateStr);
		return getDayMinTime(date.getTime());
	}
	
	/**
	 * 获取一天中最大的时间
	 * @param millis
	 * @return
	 */
	public static Timestamp getDayMaxTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return new Timestamp(calendar.getTime().getTime());
	}
	
	/**
	 * 获取一天中最大的时间
	 * @param millis
	 * @return
	 */
	public static Timestamp getDayMaxTime(String dateStr) {
		Date date = parseDate(dateStr);
		return getDayMaxTime(date.getTime());
	}
	
	/**
	 * 字符转换日期对象
	 * @param dateStr
	 * @return
	 */
	public static Date parseDate(String dateStr) {
		if (dateStr == null || dateStr.trim().equals("")) {
			return null;
		}
		dateStr = dateStr.replaceAll("/", "-");
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			return date;
		} catch (ParseException e) {}
		return null;
	}
	
	/**
	 * 日期对象转换字符
	 * @param dateStr
	 * @return 
	 */
	public static String parseDate(Date date) {
		if (date == null) {
			return "";
		}
		String s = new Timestamp(date.getTime()).toString();
		return s.substring(0, 10);
	}
	
	
	
	/**
	 * 日期对象转换字符
	 * @param dateStr
	 * @return
	 */
	public static String parseDateTime(Date date) {
		if (date == null) {
			return "";
		}
		String s = sf.format(date);
		return s;
	}
	
	/**
	 * 字符转换日期对象
	 * @param dateTimeStr
	 * @return
	 */
	public static Date parseDateTime(String dateTimeStr) {
		if (dateTimeStr == null || dateTimeStr.trim().equals("")) {
			return null;
		}
		dateTimeStr = dateTimeStr.replaceAll("/", "-");
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTimeStr);
			return date;
		} catch (ParseException e) {}
		return null;
	}
	
	/**
	 * 获取星期
	 * @param dateStr
	 * @return
	 */
	public static String getWeek(String dateStr) {
		Date date = parseDate(dateStr);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week = c.get(Calendar.DAY_OF_WEEK);
		String[] weekArray = new String[] {"", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		return weekArray[week];
	}
	
	/**
	 * 时间相加
	 * @param time 目标时间
	 * @param days 相加天数
	 * @return
	 */
	public static Timestamp add(Timestamp time, int days) {
		long timeMSEL = time.getTime();
		timeMSEL += days * 24 * 60 * 60 * 1000L;
		return new Timestamp(timeMSEL);
	}
	
	/**
	 * 去除毫秒
	 * @return 返回不包含毫秒的字符串时间
	 */
	public static String discardMSEL(Timestamp time) {
		if (time == null) {
			return "";
		}
		String value = time.toString();
		int index = value.indexOf(".");
		if (index != -1) {
			value = value.substring(0, index);	// 去除毫秒
		}
		return value;
	}
	
	public static int getCurrentYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}
	
	public static int getCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}
	
	public static Long dateDiff(Date startTime, Date endTime, String str,String returnFormat) {
		// 按照传入的格式生成一个simpledateformate对象
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		// 获得两个时间的毫秒时间差异
		diff = endTime.getTime() - startTime.getTime();
		day = diff/nd;   // 计算差多少天
		hour = (diff/nh-day*24);// 计算差多少小时
		min = ((diff/nm)-day*24*60-hour*60);// 计算差多少分钟
		sec = (diff/ns-day*24*60*60-hour*60*60-min*60);
		
		// 输出结果
		System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时"
				+ (min - day * 24 * 60) + "分钟" + sec + "秒。");
		System.out.println("hour=" + hour + ",min=" + min);
		if (returnFormat.equalsIgnoreCase("h")) {
			return hour;
		} else if(returnFormat.equalsIgnoreCase("d")){
			return day;
		}else if(returnFormat.equalsIgnoreCase("m")){
			return min;
		}else {
			return sec;
		}
	}
	
	
	/**
	* 日期转换成字符串
	* @param date 
	* @return str
	*/
	public static String dateToStr(Date date,String formatType) {
	   SimpleDateFormat format = new SimpleDateFormat(formatType);
	   String str = format.format(date);
	   return str;
	} 

	/**
	 * 字符串转换成日期
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str,String formatType) {
		SimpleDateFormat format = new SimpleDateFormat(formatType);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	
	public static void main(String[] args) {
		//DateUtil.dateDiff(DateUtil.parseDate("2014-12-14 18:00:00"),new Date(), "yyyy-MM-dd hh:mm:ss", "h");
		DateUtil.dateDiff(new Date(),new Date(), "yyyy-MM-dd hh:mm:ss", "h");
	}
}
