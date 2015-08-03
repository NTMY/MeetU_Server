package org.meetu.client.listener;

import java.util.List;

import org.meetu.model.LocationCurr;


/**
 * meetu相遇Listener interface
 * */
public interface MeetuListener {
	
	/**
	 * 客户端实现此方法
	 * */
	void meetu(List<LocationCurr> list);
	
}
