package org.meetu.client.handler;

import org.meetu.client.listener.PubDataListener;
import org.meetu.dto.PubDataDto;
import org.meetu.model.PubData;
import org.meetu.util.BeanConverter;

/**
 * 公共接口Handler<br>
 * 由客户端调用
 * */
public class PubHandler extends BaseHandler {

	/**
	 * App启动时的pubData公共数据接口
	 * */
	public void onPubData(PubDataListener listener ,PubData data) {
		String subUrl = "/pubAction!pubData?";
		StringBuffer param = new StringBuffer();
		param.append("data.os=").append(data.getOs()).append("&data.appVer=").append(data.getAppVer()).append("&data.signature=").append(data.getSignature());
		xml = send(subUrl, param.toString());
		PubDataDto dto = (PubDataDto) BeanConverter.xmlToBean(xml);
		if (listener != null) {
			listener.pubData(dto);
		}

	}
	
}
