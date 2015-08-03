package org.meetu.client.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meetu.client.listener.MeetuUploadListener;
import org.meetu.dto.BaseDto;
import org.meetu.model.LocationCurr;
import org.meetu.util.BeanConverter;
import org.meetu.util.ListBean;

import static org.meetu.client.util.HttpUtil.*;
import static org.meetu.constant.Constant.*;

/**
 * 处理meetu_upload过程 供客户端调用
 * */
public class MeetuUploadHandler {
	private static Log logger = LogFactory.getLog(MeetuUploadHandler.class);
	private static final String subUrl = "/meetuAction!upload?";

	/**
	 * 此方法供客户端调用
	 * */
	public void onUpload(MeetuUploadListener listener, LocationCurr curr) {
		StringBuffer param = new StringBuffer();
		param.append("curr.userId=").append(curr.getUserId()).append("&curr.longitude=")
				.append(curr.getLongitude()).append("&curr.latitude=")
				.append(curr.getLatitude()).append("&curr.address=")
				.append(curr.getAddress()).append("&curr.business=")
				.append(curr.getBusiness());
		String xml = sendPost(URL + subUrl, param.toString());
		logger.info("xml == " + xml);
		BaseDto dto = (BaseDto) BeanConverter.xmlToBean(xml);
		if (listener != null) {
			listener.upload(dto);
		}

	}

}
