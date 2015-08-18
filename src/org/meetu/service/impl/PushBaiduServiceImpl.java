package org.meetu.service.impl;

import org.meetu.dao.PushBaiduDao;
import org.meetu.model.PushInfoBaidu;
import org.meetu.service.IPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushBaiduServiceImpl implements IPushService {

	@Autowired
	private PushBaiduDao dao;
	
	
	@Override
	public void insertOrUpdate(PushInfoBaidu push) {
		dao.insertOrUpdate(push);
	}

	
	@Override
	public void push(String[] channelIds) {
		
	}

	
	@Override
	public void pushToAll() {
		
	}
}
