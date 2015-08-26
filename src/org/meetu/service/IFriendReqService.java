package org.meetu.service;

import java.util.List;

import org.meetu.dto.FriendReqDealDto;
import org.meetu.model.FriendReq;
import org.springframework.stereotype.Service;

public interface IFriendReqService {
	
	int insert(FriendReq req) throws Exception;

	void update(FriendReq req) throws Exception;
	
	List<FriendReq> queryList(FriendReq req);
	
	FriendReq queryById(FriendReq req) throws Exception;
	
	/**
	 * 处理好友申请
	 * */
	FriendReqDealDto dealFriendReq(FriendReq req) throws Exception;
}
