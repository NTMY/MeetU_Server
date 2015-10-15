package org.meetu.client.test;

import org.meetu.client.handler.MeetuHandler;
import org.meetu.client.listener.MeetuListener;
import org.meetu.client.listener.impl.MeetuListenerImpl;
import org.meetu.model.LocationCurr;

/**
 * 测试类
 * */
public class MeetUTest {

	public static void main(String[] args) {
		MeetuListener listener = new MeetuListenerImpl();
		LocationCurr curr = new LocationCurr();
		curr.setUserId(21);
		curr.setLatitude(1.0);
		curr.setLongitude(1.0);
		curr.setAddress("21");
		curr.setBusiness("21");
		new MeetuHandler().onMeetu(listener, curr);
	}
	
}
