package org.meetu.action;

import static org.meetu.constant.Constant.STATUS_FAIL;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.meetu.dto.BaseDto;
import org.meetu.model.PushInfoBaidu;
import org.meetu.service.IPushService;
import org.meetu.util.BeanConverter;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 推送相关动作<br>
 * 目前使用百度云推送<br>
 * 暂时不使用百度推送的tag功能,没有需求
 * */
public class PushAction extends ActionSupport {

	Log logger = LogFactory.getLog(PushAction.class);
	
	@Autowired
	private IPushService pushService;
	
	HttpServletRequest req;
	HttpServletResponse resp;
	
	PrintWriter out = null;

	/**
	 * 返给客户端xml
	 * */
	private String xml = "";
	
	/**
	 * STRUTS2注入参数
	 * */
	private PushInfoBaidu push;
	
	/**
	 * 在本地对百度推送用户信息入库
	 * */
	public String savePushInfo() {
		BaseDto dto = new BaseDto();//返回对象(转xml)
		req = ServletActionContext.getRequest();
		resp = ServletActionContext.getResponse();
		
		try {
			out = resp.getWriter();
			pushService.insertOrUpdate(push);
		} catch (IOException e) {
			dto = new BaseDto(STATUS_FAIL,"上传用户百度推送信息失败,请重试");
			logger.error(e);
			logger.error("上传用户百度推送信息失败",e);
		} finally{
			xml = BeanConverter.bean2xml(dto);
			logger.warn("上传用户百度推送信息的返回XML");
			logger.warn(xml);
			out.write(xml);
			out.close();
		}
		return null;
	}

	
	/**
	 * getters and setters
	 * */
	public PushInfoBaidu getPush() {
		return push;
	}

	public void setPush(PushInfoBaidu push) {
		this.push = push;
	}



	
	
	
	/**
	 * 推送逻辑
	 * 角色 : 客户端C/服务端S/百度Baidu
	 * C直接请求Baidu,获得channelId,userId,相当于注册,如果成功,将这两个id入库push_info_baidu
	 * 
	 * */
}
