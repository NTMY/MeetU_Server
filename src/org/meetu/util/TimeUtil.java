package org.meetu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TimeUtil {
	
	static Log log = LogFactory.getLog(TimeUtil.class);
	
	private static SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static String parseDate2Str(Date date) {
		String dateStr =  format.format(date);
		return dateStr;
	}
	
	
	public static Date parseStr2Date(String str) {
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			log.error(e.getCause());
		}
		return date;
	}
	
	
	/**
	 * 时间加减法
	 * @param originDate 原始时间
	 * @param operator +/-
	 * @param updateTime 要加减的时间
	 * @param unit 单位year/month/day/hour/minute/second
	 * @return 计算之后的date对象
	 * 这种工具类方法就不对参数进行校验了!
	 * */
	public static Date calcTime( Date originDate , String operator, long updateTime, String unit) {
		Date resultDate = null;
		if(operator.equals("+")) {
			if(unit.equals("hour")) {
				resultDate = new Date(originDate.getTime()+1000*60*60*updateTime);
			}	
		} else if(operator.equals("-")) {
			if(unit.equals("hour")) {
				resultDate = new Date(originDate.getTime()-1000*60*60*updateTime);
			}
		} else {
			log.error("======================= Sorry U r fool =======================");
		}
		
		return resultDate;
	}
}
