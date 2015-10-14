package org.meetu.service;

import java.util.List;

import org.meetu.model.FriendRel;

/**
 * 好友关系Service接口
 * */
public interface IFriendRelService {

	void insert(FriendRel rel) throws Exception;
	
	void update(FriendRel rel);
	
	/**
	 * 通过用户id查询他的好友
	 * @param userId 用户本人id
	 * @param statusRel好友关系 见Constant
	 * @throws Exception 
	 * */
	List<Object[]> queryMyFriendList(Integer userId,String statueRel) throws Exception;
	
}
