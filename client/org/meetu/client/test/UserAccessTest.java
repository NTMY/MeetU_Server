package org.meetu.client.test;

import org.meetu.client.handler.UserHandler;
import org.meetu.client.listener.UserAccessListener;
import org.meetu.client.listener.impl.UserAccessListenerImpl;
import org.meetu.model.DeviceInfo;
import org.meetu.model.User;

/**
 * 测试类
 * */
public class UserAccessTest {
	public static void main(String[] args) {
		UserAccessListener listener = new UserAccessListenerImpl();
		User user = new User();
		user.setMobile("15011448848");
		user.setPwd("123123");
		
		new UserHandler().onAccess(listener, user);
	}
}
