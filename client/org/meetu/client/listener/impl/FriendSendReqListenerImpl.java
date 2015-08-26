package org.meetu.client.listener.impl;

import org.meetu.client.listener.FriendSendReqListener;
import org.meetu.dto.BaseDto;

/**
 * For Test
 * */
public class FriendSendReqListenerImpl implements FriendSendReqListener {

	@Override
	public void sendFriendReq(BaseDto dto) {
		System.out.println("============================如果看到这句话说明调用成功===============================");
	}

}
