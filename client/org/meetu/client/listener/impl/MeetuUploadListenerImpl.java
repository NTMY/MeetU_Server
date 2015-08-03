package org.meetu.client.listener.impl;

import org.meetu.client.listener.MeetuUploadListener;
import org.meetu.dto.BaseDto;

/**
 * 模拟安卓端new的实现类
 * */
public class MeetuUploadListenerImpl implements MeetuUploadListener {


	@Override
	public void upload(BaseDto dto) {
		System.out.println("========================如果看到这句话说明正确调用了Impl=======================");		
	}
	
	
}
