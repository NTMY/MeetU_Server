package org.meetu.util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigPropUtil {
	private static Properties p = new Properties();
	
	public static void init() {
		//此处路径问题 /XXXX为从classes下开始寻找 不加/则为从当前类路径下开始寻找
		InputStream in = ConfigPropUtil.class
				.getResourceAsStream("/meetu.properties");
		try {
			p.load(in);
			if(p == null) {
				System.out.println("P NULL");
 			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static String get(String key) {
		p.getProperty(key, "123");
		return "";
	}
	
}
