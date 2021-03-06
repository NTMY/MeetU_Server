package org.meetu.client.handler;

import java.util.HashMap;
import java.util.Map;

import org.meetu.client.listener.PortraitUploadListener;
import org.meetu.client.util.HttpUtil;
import org.meetu.constant.Constant;
import org.meetu.dto.BaseDto;
import org.meetu.model.PortraitUploadModel;
import org.meetu.model.User;
import org.meetu.util.BeanConverter;

/**
 * PortraitHandler
 * */
public class PortraitHandler extends BaseHandler {
	
	/**
	 *  textMap.put("userId", "1");
        textMap.put("resolution", "");
        textMap.put("extName","jpg");
        
        fileMap.put("file", "D:/meet_me.jpg");
	 * */
	public void onUpload(PortraitUploadListener listener , PortraitUploadModel model) {
		String subUrl = "fileUploadAction!upload?";//加不加/都一样
		StringBuffer param = new StringBuffer();
		param.append("userId=").append(model.getUserId()).append("&resolution=").append(model.getResolution()).append("&fileName=").append(model.getFileName());
		Map<String, byte[]> fileMap = new HashMap<>();
		fileMap.put("file", model.getFileBytes());
		xml = HttpUtil.sendPostFileStream(Constant.URL + subUrl + param, null, fileMap);
		User user = (User) BeanConverter.xmlToBean(xml);
		if (listener != null) {
			listener.upload(user);
		}
	}
}