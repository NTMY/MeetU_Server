package org.meetu.client.listener.impl;

import org.meetu.client.listener.PushListener;
import org.meetu.dto.BaseDto;

public class PushListenerImpl implements PushListener {

	@Override
	public void savePushInfo(BaseDto dto) {
		System.out.println("========================如果看到这句话说明正确调用了Impl=======================");		
	}

}
