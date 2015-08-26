package org.meetu.client.test;

import org.meetu.client.handler.FriendHandler;
import org.meetu.client.listener.impl.FriendSendReqListenerImpl;
import org.meetu.model.FriendReq;

import static org.meetu.constant.Constant.*;

public class FriendSendReqTest {
	public static void main(String[] args) {
		FriendHandler handler = new FriendHandler();
		FriendReq req = new FriendReq();
		req.setReqUserId(1);
		req.setReqFriendId(2);
		req.setReqWay(REQ_WAY_MOBILE);
		req.setReqFriendData("15011448840");
		req.setReqMessage("加好友");
		handler.onSendFriendReq(new FriendSendReqListenerImpl(), req);
	}
}
