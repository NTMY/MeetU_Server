package org.meetu.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.meetu.constant.Constant.*;

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
			p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
			m = p.matcher(mobile);
			b = m.matches();
			return b;
		}
	}
	
	/**
	 * 检查app版本
	 * @param verDB 数据库中的最新版本号
	 * @param appVer 上传的app版本
	 * @return 0:无更新 1:建议更新 2:强制更新
	 * */
	public static String checkAppVer(String verDB , String appVer) {
		String ret = UPDATE_LV_NO;//默认不更新
		//不能直接split(".")
		String[] verDBs = verDB.trim().split("\\.");
		String[] appVers = appVer.trim().split("\\.");
		//当可以转化为int时,String的compareTo()返回值就是第一个数减第二个数
		//大版本无更新
		if(verDBs[0].compareTo(appVers[0]) == 0) {
			//小版本无更新
			if(verDBs[1].compareTo(appVers[1]) == 0) {
				//细节版本无更新
				if(verDBs[2].compareTo(appVers[2]) == 0) {
					ret = UPDATE_LV_NO;
				} else {
					//细节版本有更新
					ret = UPDATE_LV_SUGGEST;
				}
			} else {
			//小版本有更新
				ret = UPDATE_LV_SUGGEST;
			}
		} else {
			//大版本号不一致强制更新
			ret = UPDATE_LV_FORCE;
		} 
		
		
		//2.小版本号
		//3.细节版本号
		return ret;
	}
	
	/**
	 * 替换转义字符<br>
	 * 当需要调用时调用,效率低下
	 * 不行!!!!转完之后XSTREAM不认了!!!不能在服务端转
	 * * */
	public static String replaceESC(String origin) {
		String ret = "";
		ret = origin.replace("&amp;" , "&").replace("&quot;" , "\"").replace("&lt;" , "<").replace("&gt;" , ">").replace("&nbsp;" , " ");
		return ret;
	}
	
}
