package org.meetu.client.handler;

import static org.meetu.client.util.HttpUtil.sendPost;
import static org.meetu.constant.Constant.*;

import org.meetu.client.listener.PushListener;
import org.meetu.dto.BaseDto;
import org.meetu.model.PushInfoBaidu;
import org.meetu.util.BeanConverter;

/**
 * 客户端推送相关操作Handler<br>
 * 由客户端调用
 * */
public class PushHandler {

	/**
	 * 客户端提交第三方推送信息方法
	 * */
	public void onSavePushInfo(PushListener listener, PushInfoBaidu push) {
		String subUrl = "/pushAction!savePushInfo?";
		StringBuffer param = new StringBuffer();
		param.append("push.imei=").append(push.getImei())
				.append("&push.userId=").append(push.getUserId())
				.append("&push.channelId=").append(push.getChannelId());
		String xml = sendPost(URL + subUrl, param.toString());
		BaseDto dto = (BaseDto) BeanConverter.xmlToBean(xml);
		if (listener != null) {
			listener.savePushInfo(dto);
		}

	}

}
