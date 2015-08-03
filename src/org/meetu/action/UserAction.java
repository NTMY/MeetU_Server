package org.meetu.action;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.meetu.dto.UserAccessDto;
import org.meetu.model.User;
import org.meetu.service.UserService;
import org.meetu.util.BeanConverter;
import org.meetu.util.SecureUtil;

import com.opensymphony.xwork2.ActionSupport;

import static org.meetu.constant.Constant.*;
import static org.meetu.util.CheckUtil.*;

/**
 * 
 * */
public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private static Log logger = LogFactory.getLog(UserAction.class);

	private UserService userService;

	HttpServletRequest request = null;

	HttpServletResponse response = null;

	PrintWriter out = null;

	/**
	 * 写回的xml
	 * */
	String retXml = "";

	/**
	 * STRUTS2 传递参数对象
	 * */
	private User user;

	/**
	 * 用户注册/登录
	 * 
	 * @param user
	 *            .mobile 手机号
	 * @param user
	 *            .pwd 密码
	 * */
	public String access() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();

		UserAccessDto accessDto = new UserAccessDto();// 返回的对象/转xml
		try {
			out = response.getWriter();
			user.setStatus(STATUS_USER_COMMON);
			if (user.getMobile() == null || user.getPwd() == null
					|| !checkMobile(user.getMobile())) {
				accessDto.setErrCode(STATUS_ILLEGAL_PARAM);
				retXml = BeanConverter.bean2xml(accessDto);
				out.write(retXml);
				return null;
			}
			List<User> list = userService.queryList(user);
			if (list != null && list.size() == 1) {
				// 如果用户已经存在
				if (!list.get(0).getPwd().equals(user.getPwd())) {
					// 如果密码错误
					accessDto.setErrCode(STATUS_LOGIN_ERR);
					retXml = BeanConverter.bean2xml(accessDto);
					out.write(retXml);
					return null;
				}
				user = list.get(0);
				accessDto = new UserAccessDto(ACCESS_STATUS_LOGIN, user);
			} else if (list != null && list.size() == 0) {
				// 如果用户不存在
				int pk = userService.insert(user);
				accessDto = new UserAccessDto(ACCESS_STATUS_REG, user);
			} else {
				// 如果查询结果为NULL或者数量大于1
				accessDto.setErrCode(STATUS_FAIL);
				accessDto.setErrMsg("用户查询异常");
			}
			// 对象转为xml字符串,写回jar
			retXml = BeanConverter.bean2xml(accessDto);
		} catch (Exception e) {
			accessDto.setErrCode(STATUS_FAIL);
			accessDto.setErrMsg("用户接入异常");
			logger.error(e);
		} finally {
			logger.warn("用户接入ACCESS接口返回XML");
			logger.warn(retXml);
			out.write(retXml);
			out.close();
		}
		return null;
	}

	public String update() throws ClassNotFoundException, IOException {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		
		InputStream inputStream = request.getInputStream();
		ObjectInputStream objectInputStream = new ObjectInputStream(
				new BufferedInputStream(inputStream));
		User user = (User) objectInputStream.readObject();
		objectInputStream.close();
	
		return null;
	}

	/************************************************************
	 * getters and setters
	 *************************************************************/
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
