package org.meetu.service.impl;

import java.util.List;

import org.meetu.dao.FriendReqDao;
import org.meetu.model.FriendReq;
import org.meetu.service.IFriendReqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FriendReqServiceImpl implements IFriendReqService {

	/***/
	@Autowired
	private FriendReqDao reqDao;

	@Override
	public int insert(FriendReq req) throws Exception {
		int pk = reqDao.insert(req);
		return pk;
	}

	@Override
	public void update(FriendReq req) throws Exception {
		reqDao.update(req);
	}

	@Override
	public List<FriendReq> queryList(FriendReq req) {
		List<FriendReq> list = reqDao.selectListAll(req);
		return list;
	}

	@Override
	public FriendReq queryById(FriendReq req) throws Exception {
		req = reqDao.selectById(req);
		return req;
	}
	
	

	/***********************************************************************************
	 * 
	 * getters and setters
	 * 
	 ***********************************************************************************/
	/**
	 * @return the reqDao
	 */
	public FriendReqDao getReqDao() {
		return reqDao;
	}

	/**
	 * @param reqDao the reqDao to set
	 */
	public void setReqDao(FriendReqDao reqDao) {
		this.reqDao = reqDao;
	}


	
	
}
