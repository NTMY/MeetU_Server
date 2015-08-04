package org.meetu.constant;

/**
 * 时间格式ENUM
 * */
public enum TimeFormat {

	/**完整时间(日期+时间)*/
	FULLTIME("yyyy-MM-dd HH:mm:ss"),
	/**日期*/
	DATE("yyyy-MM-dd"),
	/**时间*/
	TIME("HH:mm:ss");
	
	/**
	 * 用来存放具体String格式
	 * */
	String format = "";
	
	/**
	 * 枚举不支持public构造方法
	 * */
	TimeFormat() {
		
	}

	/**
	 * 枚举不支持public构造方法
	 * */
	TimeFormat(String format) {
		this.format = format;
	}
}
