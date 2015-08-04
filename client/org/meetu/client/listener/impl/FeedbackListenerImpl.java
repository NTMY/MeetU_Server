package org.meetu.client.listener.impl;

import org.meetu.client.listener.FeedbackListener;
import org.meetu.dto.BaseDto;

public class FeedbackListenerImpl implements FeedbackListener {

	@Override
	public void feedback(BaseDto dto) {
		System.out.println("========================如果看到这句话说明正确调用了Impl=======================");		
	}

}
