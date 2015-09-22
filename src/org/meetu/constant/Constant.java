package org.meetu.constant;

/**
 * 常量类<br>
 * interface中属性默认为public static final
 * */
public interface Constant {

	/** XML头信息 */
	String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>";

	String BAIDU_DEV_SN = "BAIDU_DEV_SN";

	/** 访问令牌，可通过该值获得开发者app的信息 */
	String BAIDU_PUSH_APIKEY = "BAIDU_PUSH_APIKEY";

	/** 与apiKey成对出现，用于app的合法身份验证 */
	String BAIDU_PUSH_SECRETKEY = "BAIDU_PUSH_SECRETKEY";

	/** value为对象 */
	String BAIDU_PUSH_PAIR = "BAIDU_PUSH_PAIR";

	/** value为对象 */
	String BAIDU_PUSH_CLIENT = "BAIDU_PUSH_CLIENT";

	/** PUSH CHANNELID临时测试用MOTO X PRO */
	String channelId = "3545744288033740498";

	/** 百度LBS服务输出数据格式 */
	String BAIDU_LBS_OUTPUT = "xml";

	/** 百度LBS服务是否搜索附近 */
	String BAIDU_LBS_POIS = "0";

	/** LBS组合内容为PRE+SUFF */
	/** 百度LBS URL前缀 */
	String BAIDU_LBS_URL_PRE = "http://api.map.baidu.com/geocoder/v2/?";
	/** 百度LBS URL后缀 */
	String BAIDU_LBS_URL_SUFF = "ak=" + BAIDU_DEV_SN + "&output="
			+ BAIDU_LBS_OUTPUT + "&pois=" + BAIDU_LBS_POIS + "&location=";

	/** HTTP超时时间(ms) */
	int TIMEOUT_HTTP = 30000;

	/** DATA状态 */
	/** 正常状态 */
	String USER_STATUS_COMMON = "0";

	/** 冻结状态 */
	String USER_STATUS_FROZEN = "10";

	/** 管理员级别 90-98 */
	String USER_STATUS_ADMIN = "90";
	/** ROOT用户级别(最高) */
	String USER_STATUS_ROOT = "99";
	/** 成功状态码 */
	String STATUS_SUCCESS = "0";
	/** 失败状态码 */
	String STATUS_FAIL = "1";
	/** 参数异常 或 数据格式错误 */
	String STATUS_ILLEGAL_PARAM = "2";
	/** 登录异常 */
	String STATUS_LOGIN_ERR = "3";

	/** 状态为注册 */
	String ACCESS_STATUS_REG = "REG";
	/** 状态为登录 */
	String ACCESS_STATUS_LOGIN = "LOGIN";

	/**
	 * 好友请求原始状态
	 * */
	String REQ_STATUS_ORIGIN = "0";
	/**
	 * 好友请求已通过
	 * */
	String REQ_STATUS_AGREE = "1";
	/**
	 * 好友请求已拒绝
	 * */
	String REQ_STATUS_REFUSE = "2";
	/**
	 * 好友请求忽略状态
	 * */
	String REQ_STATUS_IGNORE = "3";
	/**
	 * 好友请求永远忽略
	 * */
	String REQ_STATUS_IGNORE_FOREVER = "4";

	/** 好友关系状态 */
	/**
	 * 好友关系正常状态<br>
	 * 抓紧时间
	 * */
	String REL_STATUS_NORMAL = "0";
	/**
	 * 好友关系亲密状态<br>
	 * 快上!
	 */
	String REL_STATUS_INTIMATE = "1";
	/**
	 * 好友关系黑名单<br>
	 * 小伙子你还有那么点机会
	 */
	String REL_STATUS_BLACKED = "2";
	/**
	 * 好友关系已删除<br>
	 * 都是曾经了...
	 */
	String REL_STATUS_DELETED = "3";

	// 123.57.158.124 aliyun
	// localhost
	/**
	 * SERVER_IP
	 * */
	String IP = "123.57.158.124";

	/** 域名 */
	String DOMAIN = "gaowen.me";



	/**
	 * SERVER_PORT<br>
	 * aliyun的APACHE设置为88, 80端口要留给blog
	 * */
	String PORT = "88";
	
	/**
	 * HTTPS端口<br>
	 * tomcat https默认为8443<br>
	 * apache
	 * */
	String PORT_HTTPS = "8443";

	String PROTOCAL_HTTP = "http";

	String PROTOCAL_HTTPS = "https";

	String SERVER_NAME = "";

	String URL = PROTOCAL_HTTP + "://" + IP + ":" + PORT + "/" + SERVER_NAME;

	String URL_HTTPS = PROTOCAL_HTTPS + "://" + IP + ":" + PORT_HTTPS + "/" + SERVER_NAME;
	
	/** FEEDBACK反馈页面成功提示 */
	String FEEDBACK_URL = URL+"/feedback/index.html";
	
	String OS_NAME_ANDROID = "ANDROID";
	String OS_NAME_IOS = "IOS";

	/** 头像上传的路径 */
	String PIC_PATH = "D:\\";

	/** 推送 */
	int PUSHTYPE_PUSH = 1;
	/** 透传消息 */
	int PUSHTYPE_MSG = 0;

	/** 添加好友途径:手机 */
	String REQ_WAY_MOBILE = "MOBILE";
	/** 添加好友途径:姓名 */
	String REQ_WAY_NAME = "NAME";
	/** 添加好友途径:昵称 */
	String REQ_WAY_NICKNAME = "NICKNAME";
	/** 添加好友途径:QQ */
	String REQ_WAY_QQ = "QQ";
	/** 添加好友途径:微信 */
	String REQ_WAY_WECHAT = "WECHAT";
	/** 添加好友途径:邮件 */
	String REQ_WAY_EMAIL = "EMAIL";
	
	
	/**
	 * APP版本更新建议
	 * */
	/**无更新*/
	String UPDATE_LV_NO = "0";
	/** 建议更新 */
	String UPDATE_LV_SUGGEST = "1";
	/** 强制更新 */
	String UPDATE_LV_FORCE = "2";
}
