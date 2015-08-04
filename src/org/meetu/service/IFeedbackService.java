package org.meetu.service;

import org.meetu.model.Feedback;

/**
 * 用户反馈信息Service接口
 * */
public interface IFeedbackService {

	
	void insert(Feedback feed) throws Exception;
	
}
