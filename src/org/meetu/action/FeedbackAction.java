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
import org.meetu.dao.PushBaiduDao;
import org.meetu.dto.BaseDto;
import org.meetu.dto.PushBaiduParam;
import org.meetu.model.Feedback;
import org.meetu.model.PushInfoBaidu;
import org.meetu.model.User;
import org.meetu.service.IFeedbackService;
import org.meetu.service.IUserService;
import org.meetu.util.BeanConverter;
import org.springframework.beans.factory.annotation.Autowired;

import com.baidu.yun.push.sample.AndroidPushMsgToSingleDevice;

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
	private PushBaiduDao pushDao;
	
	
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
			List pushList = pushDao.queryPushInfo(ids);
			Iterator<Object[]> it = pushList.iterator();
			int count = 0;
			while (it.hasNext()) {
				Object[] obj = it.next();
				PushBaiduParam p = new PushBaiduParam();//构造推送参数对象
				p.setChannelId(obj[2].toString());
//				p.setChannelId("3545744288033740498"); //zte 3605930564105372081  //moto 3545744288033740498
				p.setType(Constant.PUSHTYPE_PUSH);// 1推送通知 0透传消息
				p.setTitle("有人反馈信息啦!");
				p.setDeviceType(3);
				p.setDesc("反馈人 : " + feed.getUserId() + "反馈内容 : " + feed.getContent());
				logger.info("---------- push to      " + ids.get(count));
				logger.info("---------- push content " + p.getDesc());
				AndroidPushMsgToSingleDevice.push(p);
				count ++;
			}
		} catch (Exception e) {
			logger.error(e);
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
