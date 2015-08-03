package org.meetu.client.listener.impl;

import org.meetu.client.listener.UserUpdateListener;
import org.meetu.dto.BaseDto;

/**
 * 模拟安卓端new的实现类
 * */
public class UserUpdateListenerImpl implements UserUpdateListener {

	@Override
	public void update(BaseDto dto) {
		System.out.println("========================如果看到这句话说明正确调用了Impl=======================");
	}

	
}
