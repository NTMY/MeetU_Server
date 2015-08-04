package org.meetu.client.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meetu.client.listener.UserAccessListener;
import org.meetu.client.listener.UserUpdateListener;
import org.meetu.dto.BaseDto;
import org.meetu.dto.UserAccessDto;
import org.meetu.model.User;
import org.meetu.util.BeanConverter;

import static org.meetu.client.util.HttpUtil.*;
import static org.meetu.constant.Constant.*;

/**
 * 处理access(reg/login)过程 供客户端调用
 * */
public class UserUpdateHandler {
	private static Log logger = LogFactory.getLog(UserUpdateHandler.class);
	private static final String subUrl = "/userAction!update?";

	/**
	 * 此方法供客户端调用
	 * */
	public void onUpdate(UserUpdateListener listener, User user) {
		StringBuffer param = new StringBuffer();
		param.append("user.id=").append(user.getId());
		//手机号
		if (user.getMobile() != null && !user.getMobile().equals("")) {
			param.append("&user.mobile=").append(user.getMobile());
		}
		//姓名
		if (user.getName() != null && !user.getName().equals("")) {
			param.append("&user.name=").append(user.getName());
		}
		//密码
		if (user.getPwd() != null && !user.getPwd().equals("")) {
			param.append("&user.pwd=").append(user.getPwd());
		}
		//生日
		if (user.getBirthdate() != null && !user.getBirthdate().equals("")) {
			param.append("&user.birthdate=").append(user.getBirthdate());
		}
		//性别
		if (user.getGender() != null && !user.getGender().equals("")) {
			param.append("&user.gender=").append(user.getGender());
		}
		//QQ
		if (user.getQq() != null && !user.getQq().equals("")) {
			param.append("&user.qq=").append(user.getQq());
		}
		//Email
		if (user.getPwd() != null && !user.getPwd().equals("")) {
			param.append("&user.email=").append(user.getEmail());
		}
		
		String xml = sendPost(URL + subUrl, param.toString());
		logger.info("xml == " + xml);
		BaseDto dto = (BaseDto) BeanConverter.xmlToBean(xml);
		if (listener != null) {
			listener.update(dto);
		}

	}

}
