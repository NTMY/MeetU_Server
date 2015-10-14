package org.meetu.constant;

/**
 * 常量类<br>
 * interface中属性默认为public static final
 * */
public class Constant {

	/***********************************************************************************************************************************************************************************************************
	 * 以下为服务器(本地)动态设置
	 *********************************************************************************************************************************************************************************************************/
	/** * SERVER_IP* */
	public static String IP = "123.57.158.124";

	public static String PORT = "8081";

	public static String PORT_HTTPS = "8444";

	public static String DOMAIN = "gaowen.me";

	/**
	 * 非高清头像文件存放路径<br>
	 * <br>
	 * /home/dev/portrait/<br>
	 * D:\\_images\\portrait\\<br>
	 * */
	public static String PORTRAIT_PATH = "/home/dev/portrait/";
	/**
	 * 高清头像文件存放路径<br>
	 * D:\\_images\\portraitHD\\<br>
	 * /home/dev/portraitHD/<br>
	 * */
	public static String PORTRAIT_HD_PATH = "/home/dev/portraitHD/";

	private static String MODE = "LOCAL";
	static {
		//如果是本地开发
		if (MODE.equalsIgnoreCase("LOCAL")) {
			IP = "localhost";
			PORT = "8080";
			PORTRAIT_PATH = "D:\\_images\\portraitHD";
			PORTRAIT_HD_PATH = "D:\\_images\\portraitHD\\";
		}
	}

	/***********************************************************************************************************************************************************************************************************
	 * 以上为服务器配设置
	 *********************************************************************************************************************************************************************************************************/
	public static final String PROTOCAL_HTTP = "http";

	public static final String PROTOCAL_HTTPS = "https";

	public static final String SERVER_NAME = "";

	public static final String URL = PROTOCAL_HTTP + "://" + IP + ":" + PORT
			+ "/" + SERVER_NAME;

	public static final String URL_HTTPS = PROTOCAL_HTTPS + "://" + IP + ":"
			+ PORT_HTTPS + "/" + SERVER_NAME;

	/** FEEDBACK反馈页面成功提示 */
	public static final String FEEDBACK_URL = URL + "/feedback/index.html";

	/***********************************************************************************************************************************************************************************************************
	 * 以下为常量设置
	 *********************************************************************************************************************************************************************************************************/

	/** XML头信息 */
	public static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>";

	public static final String BAIDU_DEV_SN = "BAIDU_DEV_SN";

	/** 访问令牌，可通过该值获得开发者app的信息 */
	public static final String BAIDU_PUSH_APIKEY = "BAIDU_PUSH_APIKEY";

	/** 与apiKey成对出现，用于app的合法身份验证 */
	public static final String BAIDU_PUSH_SECRETKEY = "BAIDU_PUSH_SECRETKEY";

	/** value为对象 */
	public static final String BAIDU_PUSH_PAIR = "BAIDU_PUSH_PAIR";

	/** value为对象 */
	public static final String BAIDU_PUSH_CLIENT = "BAIDU_PUSH_CLIENT";

	/** PUSH CHANNELID临时测试用MOTO X PRO */
	public static final String channelId = "3545744288033740498";

	/** 百度LBS服务输出数据格式 */
	public static final String BAIDU_LBS_OUTPUT = "xml";

	/** 百度LBS服务是否搜索附近 */
	public static final String BAIDU_LBS_POIS = "0";

	/** LBS组合内容为PRE+SUFF */
	/** 百度LBS URL前缀 */
	public static final String BAIDU_LBS_URL_PRE = "http://api.map.baidu.com/geocoder/v2/?";
	/** 百度LBS URL后缀 */
	public static final String BAIDU_LBS_URL_SUFF = "ak=" + BAIDU_DEV_SN
			+ "&output=" + BAIDU_LBS_OUTPUT + "&pois=" + BAIDU_LBS_POIS
			+ "&location=";

	/** HTTP超时时间(ms) */
	public static final int TIMEOUT_HTTP = 30000;

	/** DATA状态 */
	/** 正常状态 */
	public static final String USER_STATUS_COMMON = "0";

	/** 冻结状态 */
	public static final String USER_STATUS_FROZEN = "10";

	/** 管理员级别 90-98 */
	public static final String USER_STATUS_ADMIN = "90";
	/** ROOT用户级别(最高) */
	public static final String USER_STATUS_ROOT = "99";
	/** 成功状态码 */
	public static final String STATUS_SUCCESS = "0";
	/** 失败状态码 */
	public static final String STATUS_FAIL = "1";
	/** 参数异常 或 数据格式错误 */
	public static final String STATUS_ILLEGAL_PARAM = "2";
	/** 登录异常 */
	public static final String STATUS_LOGIN_ERR = "3";

	/** 状态为注册 */
	public static final String ACCESS_STATUS_REG = "REG";
	/** 状态为登录 */
	public static final String ACCESS_STATUS_LOGIN = "LOGIN";

	/**
	 * 好友请求原始状态
	 * */
	public static final String REQ_STATUS_ORIGIN = "0";
	/**
	 * 好友请求已通过
	 * */
	public static final String REQ_STATUS_AGREE = "1";
	/**
	 * 好友请求已拒绝
	 * */
	public static final String REQ_STATUS_REFUSE = "2";
	/**
	 * 好友请求忽略状态
	 * */
	public static final String REQ_STATUS_IGNORE = "3";
	/**
	 * 好友请求永远忽略
	 * */
	public static final String REQ_STATUS_IGNORE_FOREVER = "4";

	/** 好友关系状态 */
	/**
	 * 好友关系正常状态<br>
	 * 抓紧时间
	 * */
	public static final String REL_STATUS_NORMAL = "0";
	/**
	 * 好友关系亲密状态<br>
	 * 快上!
	 */
	public static final String REL_STATUS_INTIMATE = "1";
	/**
	 * 好友关系黑名单<br>
	 * 小伙子你还有那么点机会
	 */
	public static final String REL_STATUS_BLACKED = "2";
	/**
	 * 好友关系已删除<br>
	 * 都是曾经了...
	 */
	public static final String REL_STATUS_DELETED = "3";

	public static final String OS_NAME_ANDROID = "ANDROID";
	public static final String OS_NAME_IOS = "IOS";

	/** 头像上传的路径 */
	public static final String PIC_PATH = "D:\\";

	/** 推送 */
	public static final int PUSHTYPE_PUSH = 1;
	/** 透传消息 */
	public static final int PUSHTYPE_MSG = 0;

	/** 添加好友途径:手机 */
	public static final String REQ_WAY_MOBILE = "MOBILE";
	/** 添加好友途径:姓名 */
	public static final String REQ_WAY_NAME = "NAME";
	/** 添加好友途径:昵称 */
	public static final String REQ_WAY_NICKNAME = "NICKNAME";
	/** 添加好友途径:QQ */
	public static final String REQ_WAY_QQ = "QQ";
	/** 添加好友途径:微信 */
	public static final String REQ_WAY_WECHAT = "WECHAT";
	/** 添加好友途径:邮件 */
	public static final String REQ_WAY_EMAIL = "EMAIL";

	/**
	 * APP版本更新建议
	 * */
	/** 无更新 */
	public static final String UPDATE_LV_NO = "0";
	/** 建议更新 */
	public static final String UPDATE_LV_SUGGEST = "1";
	/** 强制更新 */
	public static final String UPDATE_LV_FORCE = "2";

}
