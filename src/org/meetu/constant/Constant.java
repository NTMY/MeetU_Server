package org.meetu.constant;

import static org.meetu.constant.Constant.BAIDU_LBS_OUTPUT;
import static org.meetu.constant.Constant.BAIDU_LBS_POIS;
import static org.meetu.constant.Constant.BAIDU_SN;

/**
 * 常量类<br>
 * interface中属性默认为public static final
 * */
public interface Constant {
	
	/**XML头信息*/
	String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>";
	
	/**百度API KEY/SN*/
	String BAIDU_SN = "F56034a118b5fedfc66f6e39498b9c2e";
	
	/**百度LBS URL后缀*/
	
	/**百度LBS服务输出数据格式*/
	String BAIDU_LBS_OUTPUT = "xml";
	
	/**百度LBS服务是否搜索附近*/
	String BAIDU_LBS_POIS = "0";
	
	/**LBS组合内容为PRE+SUFF*/
	/**百度LBS URL前缀*/
	String BAIDU_LBS_URL_PRE = "http://api.map.baidu.com/geocoder/v2/?";
	/**百度LBS URL后缀*/
	String BAIDU_LBS_URL_SUFF = "ak="+BAIDU_SN+"&output="+BAIDU_LBS_OUTPUT+"&pois="+BAIDU_LBS_POIS+"&location=";
	
	/**HTTP超时时间(ms)*/
	int TIMEOUT_HTTP = 10000;
	
	/**DATA状态*/
	/**正常状态*/
	String STATUS_USER_COMMON = "0";
	/**冻结状态*/
	String STATUS_USER_FROZEN = "1";
	
	
	
	/**成功状态码*/
	String STATUS_SUCCESS = "0";
	/**失败状态码*/
	String STATUS_FAIL = "1";
	/**参数异常 或 数据格式错误*/
	String STATUS_ILLEGAL_PARAM = "2";
	/**登录异常*/
	String STATUS_LOGIN_ERR = "3";
	
	/** 状态为注册 */
	String ACCESS_STATUS_REG = "REG";
	/** 状态为登录 */
	String ACCESS_STATUS_LOGIN = "LOGIN";
	
	
	//	45.55.4.64
	// localhost
	/**SERVER_IP*/
	String IP = "45.55.4.64";
	/**SERVER_PORT*/
	String PORT = "8080";

	String PROTOCAL_HTTP = "http";

	String PROTOCAL_HTTPS = "https";
	
	String SERVER_NAME = "meetu";
	
	String URL = PROTOCAL_HTTP+"://"+IP+":"+PORT+"/"+SERVER_NAME;
	
	/** 头像上传的路径 */
	String PIC_PATH = "D:\\";
}
