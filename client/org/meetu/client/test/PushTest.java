package org.meetu.client.test;

import java.util.Random;

import org.meetu.client.handler.PushHandler;
import org.meetu.client.listener.impl.PushListenerImpl;
import org.meetu.model.PushInfoBaidu;

/**
 * 推送插入数据测试
 * */
public class PushTest {
	public static void main(String[] args) {
		PushHandler handler = new PushHandler();
		PushInfoBaidu push = new PushInfoBaidu();
		push.setImei("IMEI"+new Random(10).nextInt());
		push.setChannelId("CHANNEL_ID"+new Random(10).nextInt());
		push.setUserId(111);
		handler.onSavePushInfo(new PushListenerImpl(), push);
	}
}
