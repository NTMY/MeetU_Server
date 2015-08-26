package org.meetu.client.listener;

import org.meetu.dto.BaseDto;

/**
 * 推送Listener接口
 * */
public interface PushListener {

	void savePushInfo(BaseDto dto);
}
