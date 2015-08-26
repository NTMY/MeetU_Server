package com.baidu.yun.push.sample.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meetu.cache.Cache;
import org.meetu.constant.Constant;

import com.baidu.yun.push.auth.PushKeyPair;

/**
 * 百度云推送基类
 * */
public class PushBaiduDef {

	public static Log logger = LogFactory.getLog(PushBaiduDef.class);
	/**
	 * 定义参数对象,不用每个类都定义一遍了,太多了
	 * */
	protected final static PushKeyPair pair = new PushKeyPair(Cache.get(Constant.BAIDU_PUSH_APIKEY), Cache.get(Constant.BAIDU_PUSH_SECRETKEY));
	
}
