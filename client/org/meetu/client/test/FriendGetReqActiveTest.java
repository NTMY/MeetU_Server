package org.meetu.client.test;

import org.meetu.client.handler.FriendHandler;
import org.meetu.client.listener.impl.FriendGetReqActiveListenerImpl;
import org.meetu.model.FriendReq;

/**
 * 主动获取好友申请测试
 * */
public class FriendGetReqActiveTest {
	public static void main(String[] args) {
		FriendHandler handler = new FriendHandler();
		FriendReq req = new FriendReq();
		req.setReqFriendId(1);
		handler.onGetFriendReqActive(new FriendGetReqActiveListenerImpl(), req);
	}
}
