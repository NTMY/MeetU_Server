package org.meetu.model;

import java.util.Date;

/**
 * 好友请求实体类
 * 
 * drop table if exists u_friends_req; create table u_friends_req ( reqId int
 * primary key auto_increment comment '好友申请数据id(主键自增)', reqUserId int(16) not
 * null comment '用户id(申请人)', reqFriendId int(16) not null comment
 * '想添加好友的id(被申请人)', reqWay varchar(16) not null comment '添加途径
 * ID/MOBILE/NAME/QQ/EMAIL', reqFriendData varchar(64) not null comment
 * '想添加好友的数据 与way对应', reqMessage varchar(512) not null comment '请求留言', reqStatus
 * varchar(4) not null comment '请求的状态 0:已提交,未处理 1:已同意 2:已拒绝 3:已忽略 4忽略并拒绝此人再次添加',
 * reqTime timestamp comment '请求发送的时间', respTime timestamp comment '请求处理的时间' )
 * comment '用户好友请求表,在合适的时间推送给客户端';
 * */
public class FriendReq extends BaseModel {

	private static final long serialVersionUID = -7425004151481819899L;

	/** 请求id,主键自增 */
	private Integer reqId;

	/** 用户id(申请人) */
	private Integer reqUserId;

	/** 想添加好友的id(被申请人) */
	private Integer reqFriendId;

	/** 添加途径 ID/MOBILE/NAME/QQ/EMAIL */
	private String reqWay;

	/** 想添加好友的数据 与way对应 */
	private String reqFriendData;

	/** 请求留言 */
	private String reqMessage;

	/**
	 * 请求的状态 <br>
	 * 0:已提交,未处理 1:已同意 2:已拒绝 3:已忽略 4忽略并拒绝此人再次添加
	 */
	private String reqStatus;

	/** 请求发送的时间 */
	private Date reqTime;

	/** 请求处理的时间 */
	private Date respTime;

	/**
	 * 构造方法constructor
	 * */
	public FriendReq() {

	}

	/**
	 * @return the reqId
	 */
	public Integer getReqId() {
		return reqId;
	}

	/**
	 * @param reqId
	 *            the reqId to set
	 */
	public void setReqId(Integer reqId) {
		this.reqId = reqId;
	}

	/**
	 * @return the reqUserId
	 */
	public Integer getReqUserId() {
		return reqUserId;
	}

	/**
	 * @param reqUserId
	 *            the reqUserId to set
	 */
	public void setReqUserId(Integer reqUserId) {
		this.reqUserId = reqUserId;
	}

	/**
	 * @return the reqFriendId
	 */
	public Integer getReqFriendId() {
		return reqFriendId;
	}

	/**
	 * @param reqFriendId
	 *            the reqFriendId to set
	 */
	public void setReqFriendId(Integer reqFriendId) {
		this.reqFriendId = reqFriendId;
	}

	/**
	 * @return the reqWay
	 */
	public String getReqWay() {
		return reqWay;
	}

	/**
	 * @param reqWay
	 *            the reqWay to set
	 */
	public void setReqWay(String reqWay) {
		this.reqWay = reqWay;
	}

	/**
	 * @return the reqFriendData
	 */
	public String getReqFriendData() {
		return reqFriendData;
	}

	/**
	 * @param reqFriendData
	 *            the reqFriendData to set
	 */
	public void setReqFriendData(String reqFriendData) {
		this.reqFriendData = reqFriendData;
	}

	/**
	 * @return the reqMessage
	 */
	public String getReqMessage() {
		return reqMessage;
	}

	/**
	 * @param reqMessage
	 *            the reqMessage to set
	 */
	public void setReqMessage(String reqMessage) {
		this.reqMessage = reqMessage;
	}

	/**
	 * @return the reqTime
	 */
	public Date getReqTime() {
		return reqTime;
	}

	/**
	 * @param reqTime
	 *            the reqTime to set
	 */
	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}

	/**
	 * @return the respTime
	 */
	public Date getRespTime() {
		return respTime;
	}

	/**
	 * @param respTime
	 *            the respTime to set
	 */
	public void setRespTime(Date respTime) {
		this.respTime = respTime;
	}

	/**
	 * @return the reqStatus
	 */
	public String getReqStatus() {
		return reqStatus;
	}

	/**
	 * @param reqStatus
	 *            the reqStatus to set
	 */
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
}
