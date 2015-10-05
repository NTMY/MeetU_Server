package org.meetu.client.listener.impl;

import org.meetu.client.listener.PortraitUploadListener;
import org.meetu.model.User;

public class PortraitUploadListenerImpl implements PortraitUploadListener {

	@Override
	public void upload(User dto) {
		System.out.println("-----------------看到这句话说明正确调用-----------------");
	}
	
}
