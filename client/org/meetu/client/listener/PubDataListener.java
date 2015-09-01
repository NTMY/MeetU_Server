package org.meetu.client.listener;

import org.meetu.dto.PubDataDto;

/**
 * 公共报文Listener Interface
 * */
public interface PubDataListener {

	/**
	 * 客户端实现此方法
	 * */
	void pubData(PubDataDto data);
	
}
