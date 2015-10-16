package org.meetu.action;

import static org.meetu.constant.Constant.STATUS_FAIL;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.meetu.constant.Constant;
import org.meetu.dto.BaseDto;
import org.meetu.dto.PushBaiduParam;
import org.meetu.model.Feedback;
import org.meetu.model.User;
import org.meetu.service.IFeedbackService;
import org.meetu.service.IPushService;
import org.meetu.service.IUserService;
import org.meetu.util.BeanConverter;
import org.springframework.beans.factory.annotation.Autowired;

import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;

/**
 * 用户反馈Action
 * */
public class FeedbackAction {

	Log logger = LogFactory.getLog(FeedbackAction.class);

	@Autowired
	private IFeedbackService service;

	@Autowired
	private IUserService userService;

	@Autowired
	private IPushService pushService;
	
	
	
	
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String feedback() {
		req = ServletActionContext.getRequest();
		resp = ServletActionContext.getResponse();

		BaseDto dto = new BaseDto();// 返回对象(转xml)
		try {
			out = resp.getWriter();
			feed.setFeedbackTime(new Date());
			service.insert(feed);
			
			/**给所有ROOT级别的管理员发送推送*/
			// 查询LEVEL >= 90的所有用户
			List<User> userList = userService.selectByLevel(99, ">=");
			List ids = new ArrayList();
			Iterator<User> idIt = userList.iterator();
			while(idIt.hasNext()) {
				ids.add(idIt.next().getId());
			}
			//查询出所有root管理员的推送用户信息,准备给他们推送
			List pushList = pushService.queryPushInfo(ids);
			//如果查询不到root级别管理员,直接返回
			if(pushList == null || pushList.size() ==0) {
				logger.warn("查询不到ROOT级别管理员(NO ROOT)");
				return null;
			}
			Iterator<Object[]> it = pushList.iterator();
			int count = 0;
			PushBaiduParam[] params = new PushBaiduParam[pushList.size()];
			while (it.hasNext()) {
				Object[] obj = it.next();
				PushBaiduParam p = new PushBaiduParam();//构造推送参数对象
				p.setChannelId(obj[2].toString());
//				p.setChannelId("3545744288033740498"); //zte 3605930564105372081  //moto 3545744288033740498
				p.setType(Constant.PUSHTYPE_PUSH);// 1推送通知 0透传消息
				p.setTitle("有人反馈信息啦!");
				p.setDeviceType(3);//3android 4ios
				p.setDesc("反馈人 : " + feed.getUserId() + "反馈内容 : " + feed.getContent());
				params[count] = p;
				count ++;
			}
			pushService.pushToTarget(params);//调用百度推送
		} catch(PushClientException | PushServerException pushEx) {
			logger.error(pushEx);
			dto = new BaseDto(STATUS_FAIL, "反馈信息提交成功,推送失败");
		} catch (Exception e) {
			logger.error(e);
			logger.error("用户反馈信息失败",e);
			dto = new BaseDto(STATUS_FAIL, "用户反馈信息失败,请重试");
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
