package org.meetu.client.handler;

import org.meetu.client.listener.FriendDealReqListener;
import org.meetu.client.listener.FriendGetMyFriendListListener;
import org.meetu.client.listener.FriendGetReqActiveListener;
import org.meetu.client.listener.FriendSendReqListener;
import org.meetu.dto.BaseDto;
import org.meetu.model.FriendRel;
import org.meetu.model.FriendReq;
import org.meetu.util.BeanConverter;
import org.meetu.util.ListBean;


/**
 * 好友相关操作Handler<br>
 * 由andriod调用
 * */
public class FriendHandler extends BaseHandler {

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
		xml = send(subUrl, param.toString());
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
		xml = send(subUrl, param.toString());
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
		
		xml = send(subUrl, param.toString());
		ListBean beans = (ListBean) BeanConverter.xmlToBean(xml);
		
		if (listener != null) {
			listener.getFriendReqActive(beans);
		}
	}

	/**
	 * 获取好友列表
	 * */
	public void onGetMyFriendList(FriendGetMyFriendListListener listener , FriendRel rel) {
		String subUrl = "/friendAction!getMyFriendList?";
		StringBuffer param = new StringBuffer();
		param.append("&userId=").append(rel.getPk().getUserId()).append("&statusRel=").append(rel.getStatusRel());
		
		xml = send(subUrl, param.toString());
		ListBean beans = (ListBean) BeanConverter.xmlToBean(xml);
		
		if (listener != null) {
			listener.getMyFriendList(beans);
		}
	}
}
