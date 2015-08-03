package org.meetu.client.listener.impl;

import org.meetu.client.listener.UserAccessListener;
import org.meetu.dto.UserAccessDto;

/**
 * 模拟安卓端new的实现类
 * */
public class UserAccessListenerImpl implements UserAccessListener {

	@Override
	public void access(UserAccessDto dto) {
		System.out.println("========================如果看到这句话说明正确调用了Impl=======================");
	}
	
	
}
