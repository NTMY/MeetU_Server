package org.meetu.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.meetu.dto.BaseDto;
import org.meetu.dto.UserAccessDto;
import org.meetu.model.User;
import org.meetu.service.IUserService;
import org.meetu.util.BeanConverter;
import org.meetu.util.CheckUtil;
import org.meetu.util.ListBean;
import org.meetu.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import static org.meetu.constant.Constant.*;
import static org.meetu.util.CheckUtil.*;

/**
 * 用户
 * */
public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private static Log logger = LogFactory.getLog(UserAction.class);
	
	@Autowired
	private IUserService userService;

	
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
			user.setStatus(USER_STATUS_COMMON);
			if (user.getMobile() == null || user.getPwd() == null) {
				accessDto.setErrCode(STATUS_ILLEGAL_PARAM);
				accessDto.setErrMsg("上传参数异常");
				retXml = BeanConverter.bean2xml(accessDto);
				return null;
			}
			if(!checkMobile(user.getMobile())) {
				accessDto.setErrCode(STATUS_ILLEGAL_PARAM);
				accessDto.setErrMsg("手机号格式非法");
				retXml = BeanConverter.bean2xml(accessDto);
				return null;
			}
			List<User> list = userService.queryList(user);
			if (list != null && list.size() == 1) {
				// 如果用户已经存在
				if (!list.get(0).getPwd().equals(user.getPwd())) {
					// 如果密码错误
					accessDto.setErrCode(STATUS_LOGIN_ERR);
					accessDto.setErrMsg("密码错误");
					retXml = BeanConverter.bean2xml(accessDto);
					return null;
				}
				user = list.get(0);
				accessDto = new UserAccessDto(ACCESS_STATUS_LOGIN, user);
			} else if (list != null && list.size() == 0) {
				// 如果用户不存在
				user.setRegtime(TimeUtil.parseDate2Str(new Date()));
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
			logger.error("用户接入ACCESS异常",e);
		} finally {
			logger.warn("用户接入ACCESS接口返回XML");
//			retXml = CheckUtil.replaceESC(retXml);//不能在server端进行转换,否则客户端xsteam无法解析
			logger.warn(retXml);
			out.write(retXml);
			out.close();
		}
		return null;
	}

	/**
	 * 更新用户资料接口
	 * */
	public String update() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		BaseDto dto = new BaseDto();// 返回的BaseDto对象
		try {
			out = response.getWriter();
			// user对象为客户端上传的对象
			// 根据上传用户信息查询用户,userDB为数据库里的对象
			User userDB = userService.queryById(user);

			if(userDB == null) {
				dto.setErrCode(STATUS_FAIL);
				dto.setErrMsg("用户不存在,请联系我们");
				return null;
			} else {
//				user.setRegtime(userDB.getRegtime());//用户注册时间不能被客户端更新
//				user.setStatus(userDB.getStatus());//用户状态status不能被客户端更新
				userDB.merge(user);//将上传用户的信息merge到db中的用户并update
				userService.update(userDB);
			}
		} catch (Exception e) {
			logger.error(e);
			logger.error("用户更新资料异常",e);
			dto.setErrCode(STATUS_FAIL);
			dto.setErrMsg("用户更新资料异常");
		} finally {
			retXml = BeanConverter.bean2xml(dto);
			logger.warn("用户修改资料UPDATE接口返回XML");
			logger.warn(retXml);
			out.write(retXml);
			out.close();
		}
		return null;
	}

	
	/**
	 * 查询用户
	 * */
	public String query() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		List<User> list = new ArrayList<>();
		ListBean<User> beans = new ListBean<>();//返回的对象
		try {
			out = response.getWriter();
			//查询用户
			list = userService.queryList(user);
			beans.setList(list);
		} catch (IOException e) {
			logger.error(e);
			logger.error("用户查询异常",e);
			beans.setErrCode(STATUS_FAIL);
			beans.setErrMsg("查询用户失败");
		} finally {
			retXml = BeanConverter.bean2xml(beans);
			logger.warn("查询用户QUERY接口返回XML");
			logger.warn(retXml);
			out.write(retXml);
			out.close();
		}
		
		return null;
		
	}
	
	
	/************************************************************
	 * getters and setters
	 *************************************************************/

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
