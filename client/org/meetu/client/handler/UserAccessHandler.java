package org.meetu.client.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meetu.client.listener.UserAccessListener;
import org.meetu.dto.UserAccessDto;
import org.meetu.model.User;
import org.meetu.util.BeanConverter;

import static org.meetu.client.util.HttpUtil.*;
import static org.meetu.constant.Constant.*;

/**
 * 处理access(reg/login)过程
 * 供客户端调用
 * */
public class UserAccessHandler {
	private static Log logger = LogFactory.getLog(UserAccessHandler.class);
	private static final String subUrl = "/userAction!access?"; 
	
	/**
	 * 此方法供客户端调用
	 * */
	public void onAccess(UserAccessListener listener, User user) {
		// user.mobile=15011448840&user.pwd=1231231
		StringBuffer param = new StringBuffer();
		param.append("user.mobile=").append(user.getMobile()).append("&user.pwd=").append(user.getPwd());
		String xml = sendPost(URL+subUrl , param.toString());
		logger.info("xml == " + xml);
		UserAccessDto bean = (UserAccessDto)BeanConverter.xmlToBean(xml);
		if(listener != null) {
			listener.access(bean);
		}
		
	}

}
