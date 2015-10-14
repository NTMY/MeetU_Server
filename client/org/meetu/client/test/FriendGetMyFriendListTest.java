package org.meetu.client.test;

import org.meetu.client.handler.FriendHandler;
import org.meetu.client.listener.impl.FriendGetMyFriendListListenerImpl;
import org.meetu.constant.Constant;
import org.meetu.model.FriendRel;
import org.meetu.model.key.FriendRelPK;

/**
 * 获取好友列表测试类
 * */
public class FriendGetMyFriendListTest {
	public static void main(String[] args) {
		FriendHandler handler = new FriendHandler();
//		User user = new User();
//		user.setId(1);
		FriendRel rel = new FriendRel();
		FriendRelPK pk = new FriendRelPK();
		pk.setUserId(1);
		rel.setPk(pk);
		rel.setStatusRel(Constant.REL_STATUS_NORMAL);
		handler.onGetMyFriendList(new FriendGetMyFriendListListenerImpl(), rel);
	}
}
