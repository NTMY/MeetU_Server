package org.meetu.client.listener;

import org.meetu.dto.BaseDto;

/**
 * 用户修改listener,客户端自行实现其中方法
 * */
public interface UserUpdateListener {

	void update(BaseDto dto);
	
}
