package org.meetu.client.listener;

import org.meetu.dto.BaseDto;

/**
 * 反馈listener接口
 * */
public interface FeedbackListener {

	void feedback(BaseDto dto);
}
