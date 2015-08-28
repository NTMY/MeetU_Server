package org.meetu.service.impl;

import java.util.ArrayList;
import java.util.List;

import static org.meetu.constant.Constant.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meetu.dao.FriendReqDao;
import org.meetu.dao.PushBaiduDao;
import org.meetu.dto.PushBaiduParam;
import org.meetu.model.FriendReq;
import org.meetu.model.PushInfoBaidu;
import org.meetu.service.IPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.yun.push.sample.AndroidPushMsgToAll;
import com.baidu.yun.push.sample.AndroidPushMsgToSingleDevice;

@Service
public class PushBaiduServiceImpl implements IPushService {

	Log logger = LogFactory.getLog(PushBaiduServiceImpl.class);

	@Autowired
	private PushBaiduDao dao;

	@Autowired
	private FriendReqDao reqDao;

	@Override
	public void insertOrUpdate(PushInfoBaidu push) {
		dao.insertOrUpdate(push);
	}

	@Override
	public List<Object[]> queryPushInfo(List userIdList) {
		List<Object[]> list = dao.queryPushInfo(userIdList);
		return list;
	}

	@Override
	public void pushToTarget(String[] channelIds, PushBaiduParam param)
			throws Exception {
		for (String channelId : channelIds) {
			param.setChannelId(channelId);
			AndroidPushMsgToSingleDevice.push(param);
		}
	}

	@Override
	public void pushToTarget(PushBaiduParam... params) throws Exception {
		for (PushBaiduParam param : params) {
			AndroidPushMsgToSingleDevice.push(param);
		}
	}

	@Override
	public void pushToTarget(List<Integer> userIds, PushBaiduParam param)
			throws Exception {
		List<Object[]> objs = dao.queryPushInfo(userIds);
		for (Object[] obj : objs) {
			param.setChannelId((String) obj[2]);
			AndroidPushMsgToSingleDevice.push(param);
		}
	}

	@Override
	public void pushToAll(PushBaiduParam param) throws Exception {
		logger.info("---------- push to all users ----------");
		logger.info("---------- push content : " + param.getDesc());
		AndroidPushMsgToAll.push(param);
	}

	@Override
	public void pushFriendReqTask() throws Exception {
		logger.info("------------ 定时推送所有好友请求开始START -----------");
		FriendReq req = new FriendReq();
		req.setReqStatus(REQ_STATUS_ORIGIN);
		// 查询出所有状态为"未处理"的好友申请
		List<FriendReq> list = reqDao.selectListAll(req);
		List<Integer> friendIds = new ArrayList();
		for (FriendReq reqDB : list) {
			Integer friendId = reqDB.getReqFriendId();
			friendIds.add(friendId);
		}
		//
		PushBaiduParam param = new PushBaiduParam();
		param.setTitle("您有一个好友申请");
		param.setDeviceType(3);// 3android 4ios
		param.setType(1);// 1推送 2透传消息
		pushToTarget(friendIds, param);
		logger.info("------------ 定时推送所有好友请求结束  END -----------");
	}

}
