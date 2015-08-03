package org.meetu.client.listener;


import org.meetu.dto.BaseDto;


/**
 * meetu相遇Listener interface
 * */
public interface MeetuUploadListener {
	
	/**
	 * 客户端实现此方法
	 * */
	void upload(BaseDto dto);
	
}
