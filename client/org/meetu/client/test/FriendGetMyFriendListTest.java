package org.meetu.client.test;

import org.meetu.client.handler.FriendHandler;
import org.meetu.client.listener.impl.FriendGetMyFriendListListenerImpl;
import org.meetu.model.User;

/**
 * 获取好友列表测试类
 * */
public class FriendGetMyFriendListTest {
	public static void main(String[] args) {
		FriendHandler handler = new FriendHandler();
		User user = new User();
		user.setId(1);
		handler.onGetMyFriendList(new FriendGetMyFriendListListenerImpl(), user);
	}
}
