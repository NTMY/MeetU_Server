package org.meetu.client.test;

import org.meetu.client.handler.FriendHandler;
import org.meetu.client.listener.impl.FriendDealReqListenerImpl;
import org.meetu.constant.Constant;
import org.meetu.model.FriendReq;


public class FriendDealReqTest {
	public static void main(String[] args) {
		FriendHandler handler = new FriendHandler();
		FriendReq req = new FriendReq();
		req.setReqId(2);//
		req.setReqUserId(1);
		req.setReqFriendId(2);
		req.setReqStatus(Constant.REQ_STATUS_AGREE);
		handler.onDealFriendReq(new FriendDealReqListenerImpl(), req);
	}
}
