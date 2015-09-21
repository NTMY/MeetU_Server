package org.meetu.client.handler;

import org.meetu.client.listener.MeetuListener;
import org.meetu.client.listener.MeetuUploadListener;
import org.meetu.dto.BaseDto;
import org.meetu.model.LocationCurr;
import org.meetu.util.BeanConverter;
import org.meetu.util.ListBean;

import static org.meetu.client.util.HttpUtil.*;
import static org.meetu.constant.Constant.*;

/**
 * 处理meetu过程 供客户端调用
 * */
public class MeetuHandler extends BaseHandler{

	/**
	 * 上报用户地理位置信息,并查找附近的人<br>
	 * 此方法供客户端调用
	 * */
	public void onMeetu(MeetuListener listener, LocationCurr curr) {
		String subUrl = "/meetuAction!meetu?";
		// userId=1&longitude=50.000000&latitude=10.000000

		StringBuffer param = new StringBuffer();
		checkLocLegal(curr);
		param.append("curr.userId=").append(curr.getUserId()).append("&curr.longitude=")
				.append(curr.getLongitude()).append("&curr.latitude=")
				.append(curr.getLatitude()).append("&curr.address=")
				.append(curr.getAddress()).append("&curr.business=")
				.append(curr.getBusiness());
		xml = send(subUrl, param.toString());
		ListBean<LocationCurr> beans = (ListBean<LocationCurr>) BeanConverter.xmlToBean(xml);
		
		if (listener != null) {
			listener.meetu(beans);
		}

	}
	
	/**
	 * 上报用户地理位置信息<br>
	 * 此方法供客户端调用
	 * */
	public void onUpload(MeetuUploadListener listener, LocationCurr curr) {
		String subUrl = "/meetuAction!upload?";
		StringBuffer param = new StringBuffer();
		checkLocLegal(curr);
		param.append("curr.userId=").append(curr.getUserId()).append("&curr.longitude=")
				.append(curr.getLongitude()).append("&curr.latitude=")
				.append(curr.getLatitude()).append("&curr.address=")
				.append(curr.getAddress()).append("&curr.business=")
				.append(curr.getBusiness());
		xml = send(subUrl, param.toString());
		BaseDto dto = (BaseDto) BeanConverter.xmlToBean(xml);
		if (listener != null) {
			listener.upload(dto);
		}

	}

	/**
	 * 检查经纬度信息
	 * */
	private static void checkLocLegal(LocationCurr curr) {
		if(curr.getLongitude()!=null && curr.getLatitude()!=null) {
			//如果经纬度都是0
			if(curr.getLatitude() == 0 && curr.getLongitude() == 0) {
				throw new RuntimeException("位置信息非法");
			}
		}
	}
	
}
