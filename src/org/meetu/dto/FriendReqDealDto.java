package org.meetu.dto;

import java.util.Date;

/**
 * 处理好友请求之后返回的对象
 * */
public class FriendReqDealDto extends BaseDto {

	/** 用户id(申请人) */
	private Integer reqUserId;

	/** 想添加好友的id(被申请人) */
	private Integer reqFriendId;

	/** 给请求发起人留言 */
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

	
}
