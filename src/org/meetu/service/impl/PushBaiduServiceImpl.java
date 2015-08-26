package org.meetu.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meetu.dao.PushBaiduDao;
import org.meetu.dto.PushBaiduParam;
import org.meetu.model.PushInfoBaidu;
import org.meetu.service.IPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.sample.AndroidPushMsgToAll;
import com.baidu.yun.push.sample.AndroidPushMsgToSingleDevice;

@Service
public class PushBaiduServiceImpl implements IPushService {

	Log logger = LogFactory.getLog(PushBaiduServiceImpl.class);
	
	@Autowired
	private PushBaiduDao dao;
	
	
	@Override
	public void insertOrUpdate(PushInfoBaidu push) {
		dao.insertOrUpdate(push);
	}

	@Override
	public List<PushInfoBaidu> queryPushInfo(List userIdList) {
		List<PushInfoBaidu> list = dao.queryPushInfo(userIdList);
		return list;
	}
	
	@Override
	public void pushToTarget(String[] channelIds, PushBaiduParam param) throws Exception {
		for(String channelId : channelIds) {
			param.setChannelId(channelId);
			AndroidPushMsgToSingleDevice.push(param);
		}
	}
	
	@Override
	public void pushToTarget(PushBaiduParam... params) throws Exception {
		for(PushBaiduParam param : params) {
			AndroidPushMsgToSingleDevice.push(param);
		}
	}
	
	
	@Override
	public void pushToTarget(List<Integer> userIds, PushBaiduParam param)
			throws Exception {
		List<PushInfoBaidu> infos = this.queryPushInfo(userIds);
		for(PushInfoBaidu info : infos) {
			param.setChannelId(info.getChannelId());
			AndroidPushMsgToSingleDevice.push(param);
		}
	}
	
	
	@Override
	public void pushToAll(PushBaiduParam param) throws Exception {
		logger.info("---------- push to all users ----------");
		logger.info("---------- push content : " + param.getDesc());
		AndroidPushMsgToAll.push(param);
	}





}
