package org.meetu.client.listener.impl;

import java.util.List;

import org.meetu.client.listener.FriendGetMyFriendListListener;
import org.meetu.model.FriendRel;
import org.meetu.model.User;
import org.meetu.util.ListBean;

public class FriendGetMyFriendListListenerImpl implements
		FriendGetMyFriendListListener {

	/**
	 * 返回所有用户,user对象
	 * */
	@Override
	public void getMyFriendList(ListBean<User> beans) {
		// 返回所有用户
		List<User> list = beans.getList();
		for (User user : list) {
			System.out.println(user.getImgUrl());
		}
		System.out.println("==================看到这句话说明正确调用了===================");
	}

	/**
	 * 返回真正好友列表
	 * */
	@Override
	public void getMyFriendListReal(ListBean<Object[]> beans) {
		// 返回真正好友关系
		List<Object[]> list = beans.getList();
		for (Object obj[] : list) {
			User user = (User) obj[0];
			FriendRel rel = (FriendRel) obj[1];
		}
		System.out.println("==================看到这句话说明正确调用了===================");
	}

}
