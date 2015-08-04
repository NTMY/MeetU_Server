package org.meetu.client.test;

import org.meetu.client.handler.MeetuHandler;
import org.meetu.client.listener.MeetuUploadListener;
import org.meetu.client.listener.impl.MeetuUploadListenerImpl;
import org.meetu.model.LocationCurr;

/**
 * 测试类
 * */
public class MeetUUploadTest {
	public static void main(String[] args) {
		MeetuUploadListener listener = new MeetuUploadListenerImpl();
		LocationCurr curr = new LocationCurr();
		curr.setUserId(2);
		curr.setLatitude(50.0);
		curr.setLongitude(10.0);
		curr.setAddress("北京宣武广安门");
		curr.setBusiness("广安门商圈");
		new MeetuHandler().onUpload(listener, curr);
	}
}
