package org.meetu.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author Murphy
 * 
 * @blog http://www.cnblogs.com/dirkmurphyjava/
 *
 * @date 2014年4月4日
 *
 * @introduction 
 */

/**
 * cache core processor class
 * 
 * Singleton
 * */
public class Cache {

	private static final Log logger = LogFactory.getLog(Cache.class);
	/**
	 * core data map
	 * */
	private static final Map<String, String> cacheMap = new HashMap<String, String>();

	/**
	 * SingletonModel
	 * */
	private static Cache cache = new Cache();


	public static String get(String key) {
		String value = "";
		if (null == cacheMap) {
			logger.warn("cache map is null");
			return value;
		}
		value = cacheMap.get(key);
		return value;
	}
	
	public static String get(String key,String defaultV) {
		String value = defaultV;
		if (null == cacheMap) {
			logger.warn("cache map is null");
			return value;
		}
		value = cacheMap.get(key);
		return value;
	}
	

	public static Cache getInstance() {
		return cache;
	}

	
	
	
	/*************************************************************
	 * getters and setters
	 *************************************************************/
	public static Map<String, String> getCacheMap() {
		return cacheMap;
	}


}
