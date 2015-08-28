package org.meetu.client.listener.impl;

import org.meetu.client.listener.FriendGetReqActiveListener;
import org.meetu.util.ListBean;

public class FriendGetReqActiveListenerImpl implements FriendGetReqActiveListener {

	@Override
	public void getFriendReqActive(ListBean beans) {
		System.out.println("======================看到这句话说明调用成功======================");
	}

}
