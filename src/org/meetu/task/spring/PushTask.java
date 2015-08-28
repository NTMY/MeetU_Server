package org.meetu.task.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meetu.service.IPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 定时推送未处理好友请求任务
 * */
@Service
public class PushTask {
	Log logger = LogFactory.getLog(PushTask.class);
	
	@Autowired
	private IPushService service;
	
	/**constructor*/
	public PushTask() {
		logger.info("PushFriendReqTask定时推送为处理好友请求以创建实例");
	}

	/**
	 * 定时任务<br>
	 * 定时推送未处理好友请求任务<br>
	 * */
	public void pushFriendReqJob() {
		logger.info("---------- 定时推送好友请求任务开始 ----------");
		try {
			service.pushFriendReqTask();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		logger.info("---------- 定时推送好友请求任务结束 ----------");
	}
	
}
