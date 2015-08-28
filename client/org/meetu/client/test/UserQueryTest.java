package org.meetu.client.test;


import org.meetu.client.handler.UserHandler;
import org.meetu.client.listener.UserQueryListener;
import org.meetu.client.listener.impl.UserQueryListenerImpl;
import org.meetu.model.User;

/**
 * 测试类
 * */
public class UserQueryTest {

	

	public static void main(String[] args) {
		UserQueryListener listener = new UserQueryListenerImpl();
		User user = new User();
//		user.setId(1);
		user.setMobile("15011448840");
//		user.setName("高文");
//		user.setQq("1053186456");
//		user.setEmail("giuge@aliyun.com");
//		user.setGender("MALE");
//		user.setMood("HelloWorld");
//		user.setWechat("wechat");
//		user.setBirthdate(TimeUtil.parseDate2Str(new Date()));
		new UserHandler().onQuery(listener, user);
	}
}
