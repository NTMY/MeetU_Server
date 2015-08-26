package org.meetu.client.listener.impl;

import org.meetu.client.listener.UserQueryListener;
import org.meetu.util.ListBean;

public class UserQueryListenerImpl implements UserQueryListener {

	@Override
	public void query(ListBean beans) {
		System.out.println("看到这句话说明正确调用了");
	}

}
