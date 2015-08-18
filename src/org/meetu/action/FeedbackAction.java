package org.meetu.action;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.meetu.dto.BaseDto;
import org.meetu.dto.PushBaiduParam;
import org.meetu.model.Feedback;
import org.meetu.service.IFeedbackService;
import org.meetu.util.BeanConverter;
import org.springframework.beans.factory.annotation.Autowired;

import com.baidu.yun.push.sample.AndroidPushMsgToSingleDevice;

import static org.meetu.constant.Constant.*;

/**
 * 用户反馈Action
 * */
public class FeedbackAction {

	Log logger = LogFactory.getLog(FeedbackAction.class);

	@Autowired
	private IFeedbackService service; 
	
	HttpServletRequest req;
	HttpServletResponse resp;
	
	PrintWriter out = null;
	/**
	 * 反馈信息对象STRUTS2注入 
	 * */
	private Feedback feed;
	

	/**
	 * 返给客户端xml
	 * */
	private String xml = "";
	

	/**
	 * 用户提交反馈信息
	 * */
	public String feedback() {
		req = ServletActionContext.getRequest();
		resp = ServletActionContext.getResponse();
		
		BaseDto dto = new BaseDto();//返回对象(转xml)
		try {
			out = resp.getWriter();
			feed.setFeedbackTime(new Date());
			service.insert(feed);
			PushBaiduParam p = new PushBaiduParam();
			p.setChannelId("4187121447171541963");
			p.setType(1);// 1推送通知 0透传消息
			p.setTitle("有人反馈信息啦!");
			p.setDesc(feed.getContent());
			AndroidPushMsgToSingleDevice.push(p);
		} catch (Exception e) {
			logger.error(e);
			dto = new BaseDto(STATUS_FAIL,"用户反馈信息失败,请重试");
		} finally {
			xml = BeanConverter.bean2xml(dto);
			logger.warn("用户提交反馈信息的返回XML");
			logger.warn(xml);
			out.write(xml);
			out.close();
		}
		return null;
	}
	
	
	/****************************************************************************************
	 * 
	 * getters and setters
	 * 
	 ****************************************************************************************/
	public Feedback getFeed() {
		return feed;
	}

	public void setFeed(Feedback feed) {
		this.feed = feed;
	}
	
}
