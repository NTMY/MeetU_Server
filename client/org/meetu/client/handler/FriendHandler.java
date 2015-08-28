package org.meetu.client.handler;

import static org.meetu.client.util.HttpUtil.sendPost;
import static org.meetu.constant.Constant.URL;

import org.meetu.client.listener.FriendDealReqListener;
import org.meetu.client.listener.FriendGetReqActiveListener;
import org.meetu.client.listener.FriendSendReqListener;
import org.meetu.dto.BaseDto;
import org.meetu.model.FriendReq;
import org.meetu.util.BeanConverter;
import org.meetu.util.ListBean;

/**
 * 好友相关操作Handler<br>
 * 由andriod调用
 * */
public class FriendHandler {

	/**
	 * 发送好友请求
	 * */
	public void onSendFriendReq(FriendSendReqListener listener, FriendReq req) {
		String subUrl = "/friendAction!sendFriendReq?";
		StringBuffer param = new StringBuffer();
		param.append("req.reqUserId=").append(req.getReqUserId())
				.append("&req.reqFriendId=").append(req.getReqFriendId())
				.append("&req.reqWay=").append(req.getReqWay())
				.append("&req.reqFriendData=").append(req.getReqFriendData())
				.append("&req.reqMessage=").append(req.getReqMessage());
		String xml = sendPost(URL + subUrl, param.toString());
		BaseDto dto = (BaseDto) BeanConverter.xmlToBean(xml);

		if (listener != null) {
			listener.sendFriendReq(dto);
		}
	}

	/**
	 * 处理好友请求
	 * */
	public void onDealFriendReq(FriendDealReqListener listener, FriendReq req) {
		String subUrl = "/friendAction!dealFriendReq?";
		StringBuffer param = new StringBuffer("req.Id=00");
		param.append("&req.reqUserId=").append(req.getReqUserId())
				.append("&req.reqFriendId=").append(req.getReqFriendId())
				.append("&req.reqStatus=").append(req.getReqStatus());
		String xml = sendPost(URL + subUrl, param.toString());
		BaseDto dto = (BaseDto) BeanConverter.xmlToBean(xml);

		if (listener != null) {
			listener.dealFriendReq(dto);
		}
	}
	
	/**
	 * 客户端主动请求未处理好友申请
	 * */
	public void onGetFriendReqActive(FriendGetReqActiveListener listener ,FriendReq req) {
		String subUrl = "/friendAction!getFriendReqActive?";
		StringBuffer param = new StringBuffer();
		param.append("&req.reqFriendId=").append(req.getReqFriendId());
		
		String xml = sendPost(URL + subUrl, param.toString());
		ListBean beans = (ListBean) BeanConverter.xmlToBean(xml);
		
		if (listener != null) {
			listener.getFriendReqActive(beans);
		}
	}

}
