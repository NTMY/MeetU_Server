package org.meetu.client.listener.impl;

import java.util.List;

import org.meetu.client.listener.FriendGetMyFriendListListener;
import org.meetu.model.User;
import org.meetu.util.ListBean;

public class FriendGetMyFriendListListenerImpl implements FriendGetMyFriendListListener {

	@Override
	public void getMyFriendList(ListBean beans) {
		List<User> list = beans.getList();
		for(User user : list) {
			System.out.println(user.getImgUrl());
		}
		System.out.println("==================看到这句话说明正确调用了===================");
	}

}
