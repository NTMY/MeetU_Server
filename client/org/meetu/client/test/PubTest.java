package org.meetu.client.test;

import org.meetu.client.handler.PubHandler;
import org.meetu.client.listener.impl.PubDataListenerImpl;
import org.meetu.model.PubData;

/**
 * 公共数据pubdata接口测试
 * */
public class PubTest {
	public static void main(String[] args) {
		PubHandler handler = new PubHandler();
		PubData data = new PubData();
		data.setAppVer("1.0.2");
		data.setOs("ANDROID");
		data.setSignature("123123");
		handler.onPubData(new PubDataListenerImpl(), data);
	}
}
