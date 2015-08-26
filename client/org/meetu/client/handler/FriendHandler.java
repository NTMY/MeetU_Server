package org.meetu.client.handler;

import static org.meetu.client.util.HttpUtil.sendPost;
import static org.meetu.constant.Constant.URL;

import org.meetu.client.listener.FriendDealReqListener;
import org.meetu.client.listener.FriendSendReqListener;
import org.meetu.dto.BaseDto;
import org.meetu.model.FriendReq;
import org.meetu.util.BeanConverter;

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
		StringBuffer param = new StringBuffer();
		param.append("req.reqId=").append(req.getReqId())
				.append("&req.reqUserId=").append(req.getReqUserId())
				.append("&req.reqFriendId=").append(req.getReqFriendId())
				.append("&req.reqStatus=").append(req.getReqStatus());
		String xml = sendPost(URL + subUrl, param.toString());
		BaseDto dto = (BaseDto) BeanConverter.xmlToBean(xml);

		if (listener != null) {
			listener.dealFriendReq(dto);
		}
	}

}
