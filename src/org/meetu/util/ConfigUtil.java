package org.meetu.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * .properties配置文件调取操作类
 * */
public class ConfigUtil {

	private static final Log logger = LogFactory.getLog(ConfigUtil.class);

	private static Properties p = new Properties();

	// jdk7+ new feature
	/**
	 * 
	 * */
	public static Map<String, String> map = new HashMap<>();

	/**
	 * SERVER启动初始化资源
	 * */
	public static void init() {
		// 此处路径问题 /XXXX为从classes下开始寻找 不加/则为从当前类路径下开始寻找
		InputStream in = ConfigUtil.class
				.getResourceAsStream("/meetu.properties");
		try {
			p.load(in);
			if (p == null) {
				logger.error("---Property is NULL---");
			}
			for (Object key : p.keySet()) {
				map.put((String) key, get(String.valueOf(key)));
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * 通过key获取properties文件的value
	 * 
	 * @param key
	 *            key
	 * @param defaultValue
	 *            当取不到值时的默认值
	 * */
	public static String get(String key, String defaultValue) {
		String value = p.getProperty(key, defaultValue);
		return value;
	}

	/**
	 * 通过key获取properties文件的value
	 * 
	 * @param key
	 *            key
	 * */
	public static String get(String key) {
		String value = p.getProperty(key);
		return value;
	}

}
