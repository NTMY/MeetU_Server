package org.meetu.service;

import java.util.List;

import org.meetu.dto.PushBaiduParam;
import org.meetu.model.PushInfoBaidu;

public interface IPushService {
	
	/**
	 * 插入或者更新推送用户信息
	 * */
	void insertOrUpdate(PushInfoBaidu push);
	
	/**
	 * 通过用户查询用户推送信息
	 * */
	List<PushInfoBaidu> queryPushInfo(List userList);
	
	/**
	 * 发送推送给指定用户
	 * @param channelIds 要发送的channelId
	 * @param param 推送信息-包含发送的数据(不需要设置channelId)
	 *  */
	void pushToTarget(String[] channelIds , PushBaiduParam param) throws Exception;
	
	/**
	 * 通过userId集合,发送推送给指定用户
	 * @param userIds 用户id集合
	 * @param param 推送信息-包含要发送的数据(不需要设置channelId)
	 *  */
	void pushToTarget(List<Integer> userIds , PushBaiduParam param) throws Exception;
	
	/**
	 * 发送推送给指定用户
	 * @param params 用户推送信息(需要设置所有属性)
	 *  */
	void pushToTarget(PushBaiduParam... params) throws Exception;
	
	/**
	 * 推送给所有用户
	 */
	void pushToAll(PushBaiduParam param) throws Exception;
}
