package org.meetu.client.listener;

import org.meetu.dto.UserAccessDto;


/**
 * userAccess Listener interface
 * */
public interface UserAccessListener {
	
	/**
	 * 客户端实现此方法
	 * */
	void access(UserAccessDto dto);
	
}
