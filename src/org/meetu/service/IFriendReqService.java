package org.meetu.service;

import java.util.List;

import org.meetu.model.FriendReq;
import org.springframework.stereotype.Service;

public interface IFriendReqService {
	
	int insert(FriendReq req) throws Exception;

	void update(FriendReq req) throws Exception;
	
	List<FriendReq> queryList(FriendReq req);
	
	FriendReq queryById(FriendReq u) throws Exception;
	
	
}
