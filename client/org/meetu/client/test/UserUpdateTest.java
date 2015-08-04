package org.meetu.client.test;

import java.util.Date;

import org.meetu.client.handler.UserHandler;
import org.meetu.client.listener.UserUpdateListener;
import org.meetu.client.listener.impl.UserUpdateListenerImpl;
import org.meetu.model.User;
import org.meetu.util.TimeUtil;

/**
 * 测试类
 * */
public class UserUpdateTest {

	

	public static void main(String[] args) {
		UserUpdateListener listener = new UserUpdateListenerImpl();
		User user = new User();
		user.setId(99);
		user.setMobile("15011448840");
		user.setName("高文");
		user.setQq("1053186456");
		user.setEmail("giuge@aliyun.com");
		user.setGender("MALE");
		user.setBirthdate(TimeUtil.parseDate2Str(new Date()));
		new UserHandler().onUpdate(listener, user);
	}
}
