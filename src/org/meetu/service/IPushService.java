package org.meetu.service;

import java.util.List;

import org.meetu.model.PushInfoBaidu;

public interface IPushService {
	
	void insertOrUpdate(PushInfoBaidu push);
	
	/**
	 * 通过用户查询用户推送信息
	 * */
	List<PushInfoBaidu> queryPushInfo(List userList);
	
	/** 发送推送 */
	void push(String[] channelIds);
	
	/** 推送给所有人 */
	void pushToAll();
}
