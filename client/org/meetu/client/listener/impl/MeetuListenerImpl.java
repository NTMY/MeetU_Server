package org.meetu.client.listener.impl;

import java.util.List;

import org.meetu.client.listener.MeetuListener;
import org.meetu.model.LocationCurr;

/**
 * 模拟安卓端new的实现类
 * */
public class MeetuListenerImpl implements MeetuListener {

	@Override
	public void meetu(List<LocationCurr> list) {
		System.out.println(list.size());
		System.out.println("========================如果看到这句话说明正确调用了Impl=======================");
	}
	
	
}
