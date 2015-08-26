package org.meetu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 好友申请留言记录<br>
 * */
@Entity
@Table(name = "u_friend_msg", catalog = "meetu")
public class FriendMsg {

	/**
	 * 请求表ID
	 * */
	@Id
	@Column(name="reqId")
	private Integer reqId;

	/**
	 * 留言人
	 * */
	@Column(name="userId")
	private Integer userId;

	/**
	 * 留言内容
	 * */
	@Column(name="msg")
	private String msg;

	/**
	 * 留言时间
	 * */
	@Column(name="msgDate")
	private Date msgDate;

	/**
	 * 构造方法
	 * */
	public FriendMsg() {

	}

	/***************************************************************
	 * 
	 * getters and setters
	 * 
	 **************************************************************/
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
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the msgDate
	 */
	public Date getMsgDate() {
		return msgDate;
	}

	/**
	 * @param msgDate
	 *            the msgDate to set
	 */
	public void setMsgDate(Date msgDate) {
		this.msgDate = msgDate;
	}

}
