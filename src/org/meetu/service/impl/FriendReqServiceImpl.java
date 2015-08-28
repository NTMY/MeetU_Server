package org.meetu.service.impl;

import static org.meetu.constant.Constant.*;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
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
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
	public FriendReqDealDto dealFriendReq(FriendReq req) throws Exception {
		FriendReqDealDto dealDto = new FriendReqDealDto();
		// JDK7+才支持String的switch case
		switch (req.getReqStatus()) {
		case REQ_STATUS_AGREE: {
			// 同意 1更新REQ表 2插入REL表两条数据(互为好友)
			// 插入第一条
			FriendRel rel = new FriendRel();
			FriendRelPK pk = new FriendRelPK();
			pk.setUserId(req.getReqUserId());
			pk.setFriendId(req.getReqFriendId());
			rel.setPk(pk);
			rel.setHappenTime(new Date());
			rel.setStatusRel(REL_STATUS_NORMAL);// 设置好友关系为正常
			relDao.insert(rel);
			// 插入第二条
			FriendRel rel2 = new FriendRel();
			BeanUtils.copyProperties(rel2, rel);// 将rel的属性都给rel2
			FriendRelPK pk2 = new FriendRelPK();
			pk2.setUserId(req.getReqFriendId());
			pk2.setFriendId(req.getReqUserId());
			rel2.setPk(pk2);
			relDao.insert(rel2);
			req.setReqStatus(REQ_STATUS_AGREE);// 设置请求关系为"已同意"\
			// insert完成
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
		// reqDao.update(req);//更新req请求表状态
		FriendReq reqQuery = new FriendReq();
		reqQuery.setReqUserId(req.getReqUserId());
		reqQuery.setReqFriendId(req.getReqFriendId());
		reqQuery.setReqStatus(REQ_STATUS_ORIGIN);// 初始状态
		List<FriendReq> list = reqDao.selectListAll(reqQuery);
		if (list == null || list.size() > 1) {
			// 如果list为null或同样的好友申请数量不为0
			dealDto.setErrCode(STATUS_FAIL);
			dealDto.setErrMsg("处理好友申请异常,查询异常");
			return dealDto;
		} else {
			// 如果有一条好友申请
			reqQuery = list.get(0);
			reqQuery.setReqStatus(req.getReqStatus());// 设置状态
			reqQuery.setRespTime(new Date());//设置处理的时间
			//持久态对象自动更新,
		}

		return dealDto;
	}


}
