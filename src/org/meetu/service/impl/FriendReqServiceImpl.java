package org.meetu.service.impl;

import static org.meetu.constant.Constant.*;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meetu.dao.FriendRelDao;
import org.meetu.dao.FriendReqDao;
import org.meetu.dto.FriendReqDealDto;
import org.meetu.model.FriendRel;
import org.meetu.model.FriendReq;
import org.meetu.model.key.FriendRelPK;
import org.meetu.service.IFriendReqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendReqServiceImpl implements IFriendReqService {

	Log logger = LogFactory.getLog(FriendReqServiceImpl.class);

	/** 好友申请dao */
	@Autowired
	private FriendReqDao reqDao;

	/** 好友关系dao */
	@Autowired
	private FriendRelDao relDao;

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

	@Override
	public FriendReqDealDto dealFriendReq(FriendReq req) {
		FriendReqDealDto dealDto = new FriendReqDealDto();
		try {
			// JDK7+才支持String的switch case
			switch (req.getReqStatus()) {
			case REQ_STATUS_AGREE: {
				// 同意 1更新REQ表 2插入REL表
				FriendRel rel = new FriendRel();
				FriendRelPK pk = new FriendRelPK();
				rel.setPk(pk);
				pk.setUserId(req.getReqUserId());
				pk.setFriendId(req.getReqFriendId());
				rel.setHappenTime(new Date());
				rel.setStatusRel(REL_STATUS_NORMAL);// 设置好友关系为正常
				relDao.insert(rel);
				pk.setUserId(req.getReqFriendId());
				pk.setFriendId(req.getReqUserId());
				relDao.insert(rel);
				req.setReqStatus(REQ_STATUS_AGREE);// 设置请求关系为"已同意"
				break;
			}
			case REQ_STATUS_REFUSE: {
				// 拒绝 更新REQ表
				req.setReqStatus(REQ_STATUS_REFUSE);// 设置请求关系为"已拒绝"
				break;
			}
			case REQ_STATUS_IGNORE: {
				// 忽略 更新REQ表
				req.setReqStatus(REQ_STATUS_IGNORE);// 设置请求关系为"已忽略"
				break;
			}
			case REQ_STATUS_IGNORE_FOREVER:// 永久忽略
			{
				// 永久忽略 更新REQ表
				// 暂时没有后续手段
				req.setReqStatus(REQ_STATUS_IGNORE_FOREVER);// 设置请求关系为"永久忽略"
				break;
			}
			default:
				// 如果上述都case不到
				// 状态无效,异常
				dealDto.setErrCode(STATUS_FAIL);
				dealDto.setErrMsg("处理好友申请异常,status参数异常");
				break;
			}
			reqDao.update(req);
		} catch (Exception e) {
			logger.error(e);
			logger.error(e.getStackTrace());
		}
		return dealDto;
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
	 * @param reqDao
	 *            the reqDao to set
	 */
	public void setReqDao(FriendReqDao reqDao) {
		this.reqDao = reqDao;
	}

}
