package org.meetu.client.listener.impl;

import org.meetu.client.listener.PortraitUploadListener;
import org.meetu.dto.BaseDto;

public class PortraitUploadListenerImpl implements PortraitUploadListener {

	@Override
	public void upload(BaseDto dto) {
		System.out.println("-----------------看到这句话说明正确调用-----------------");
	}
	
}
