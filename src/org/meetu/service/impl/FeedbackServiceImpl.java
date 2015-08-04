package org.meetu.service.impl;

import org.meetu.dao.FeedbackDao;
import org.meetu.model.Feedback;
import org.meetu.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户反馈Service
 * */
@Service
public class FeedbackServiceImpl implements IFeedbackService {

	@Autowired
	private FeedbackDao dao;
	
	@Override
	public void insert(Feedback feed) throws Exception {
		dao.insert(feed);
	}

}
