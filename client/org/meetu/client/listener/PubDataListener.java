package org.meetu.client.listener;

import org.meetu.model.PubData;

/**
 * 公共报文Listener Interface
 * */
public interface PubDataListener {

	/**
	 * 客户端实现此方法
	 * */
	void pubData(PubData data);
	
}
