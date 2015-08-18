package org.meetu.service;

import org.meetu.model.PushInfoBaidu;

public interface IPushService {
	
	void insertOrUpdate(PushInfoBaidu push);
	
	/** 发送推送 */
	void push(String[] channelIds);
	
	/** 推送给所有人 */
	void pushToAll();
}
