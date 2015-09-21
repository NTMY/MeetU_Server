package org.meetu.client.handler;

import org.apache.commons.lang.StringUtils;
import org.meetu.client.util.HttpUtil;
import org.meetu.client.util.HttpsUtil;
import org.meetu.constant.Constant;


/**
 * Handler基类<br>
 * 完成HTTP/HTTPS不同协议的判断,不同URL的拼装,不同协议的通信
 * */
public class BaseHandler {

	/** HTTP协议请求地址 */
	final String HTTP_URL = Constant.URL;
	
	/** HTTPS协议请求地址 */
	final String HTTPS_URL = Constant.URL_HTTPS;
	
	/** 实际运行时的URL */
	protected String URL = "";
	
	/**
	 * 协议类型 HTTP/HTTPS<br>
	 * static全局变量<br>
	 * 只有子类有其访问权限
	 * */
	static String ProtocolType = Constant.PROTOCAL_HTTPS;
	
	/**服务器端响应的XML*/
	String xml = "";
	
	
	static {
		//默认协议为https
//		ProtocolType = Constant.PROTOCAL_HTTPS;
		//不要把判断逻辑写在static{}中,因为ProtocolType这个static全局变量有可能在运行期修改,如果在运行期修改的话,static{}静态块中逻辑并不会因为全局变量的修改而改变逻辑
		//我把判断逻辑写在构造方法中,每次修改ProtocolType全局变量.new 出来的子类都是最新的逻辑
		
		
        // 密码  
        String password = "123123";  
        // 密钥库  
        String keyStorePath = "D://tomcat.keystore";  
        // 信任库  
        String trustStorePath = "D://tomcat.keystore";  
        try {
			HttpsUtil.initHttpsURLConnection(password, keyStorePath, trustStorePath);
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	
	/**构造方法*/
	public BaseHandler() {
		if(ProtocolType.equals(Constant.PROTOCAL_HTTP)) {
			URL = HTTP_URL;
		} else {
			URL = HTTPS_URL;
		}
	}
	
	/**
	 * 设置通信类型
	 * @param type 通信类型传值HTTP/HTTPS
	 * @return 修改成功/失败
	 * */
	public void setProtocolType(String type) {
		if(StringUtils.isBlank(type)) {
			//ProtocolType = Constant.PROTOCAL_HTTPS;
		} else if(type.equals(Constant.PROTOCAL_HTTP) || type.equals(Constant.PROTOCAL_HTTPS)) {
			ProtocolType = type;
		} else {
			//ProtocolType = Constant.PROTOCAL_HTTPS;
		}
	}
	
	
	/**
	 * 在此根据协议统一发送请求
	 * */
	String send(String subUrl, String param) {
		//如果是http协议
		if(ProtocolType.equals(Constant.PROTOCAL_HTTP)) {
			xml = HttpUtil.sendPost(URL + subUrl, param);
		} 
		//如果是https协议
		else if(ProtocolType.equals(Constant.PROTOCAL_HTTPS))  {
			xml = HttpsUtil.sendPost(URL + subUrl, param);
		}
		return xml;
	}
	
	
}
