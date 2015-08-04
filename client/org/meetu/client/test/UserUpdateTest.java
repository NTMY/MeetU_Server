package org.meetu.client.test;

import org.meetu.client.handler.UserUpdateHandler;
import org.meetu.client.listener.UserUpdateListener;
import org.meetu.client.listener.impl.UserUpdateListenerImpl;
import org.meetu.model.User;

/**
 * 测试类
 * */
public class UserUpdateTest {

	

	public static void main(String[] args) {
		UserUpdateListener listener = new UserUpdateListenerImpl();
		User user = new User();
		user.setId(1);
		user.setMobile("15011448840");
		user.setName("高文");
		user.setQq("1053186456");
		new UserUpdateHandler().onUpdate(listener, user);
	}
}
