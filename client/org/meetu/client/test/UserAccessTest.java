package org.meetu.client.test;

import org.meetu.client.handler.UserAccessHandler;
import org.meetu.client.listener.UserAccessListener;
import org.meetu.client.listener.UserAccessListenerImpl;
import org.meetu.model.User;

/**
 * 测试类
 * */
public class UserAccessTest {
	public static void main(String[] args) {
		UserAccessListener listener = new UserAccessListenerImpl();
		String param =  "user.mobile=15011448840&user.pwd=1231231";
		User user = new User();
		user.setMobile("15011448840");
		user.setPwd("123123");
		new UserAccessHandler().onAccess(listener, user);
	}
}
