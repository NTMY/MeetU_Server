package org.meetu.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {

	/**
	 * 校验手机号
	 * @param mobile上传的手机号
	 * @return 正确返回true,否则false
	 * */
	public static boolean checkMobile(String mobile) {
		boolean b = false;
		if (mobile == null || mobile.equals("")) {
			return b;
		} else {
			Pattern p = null;
			Matcher m = null;
			p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
			m = p.matcher(mobile);
			b = m.matches();
			return b;
		}
	}
}
