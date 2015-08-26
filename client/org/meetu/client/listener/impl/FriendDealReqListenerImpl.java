package org.meetu.client.listener.impl;

import org.meetu.client.listener.FriendDealReqListener;
import org.meetu.dto.BaseDto;

/**
 * 
 * */
public class FriendDealReqListenerImpl implements FriendDealReqListener {
	
	@Override
	public void dealFriendReq(BaseDto dto){
		System.out.println("==================看到这句话说明正确调用了===================");
	}
}
