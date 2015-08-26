package org.meetu.model.key;

import java.io.Serializable;

/**
 * FriendRel表联合主键
 * */
public class FriendRelPK implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 用户id */
	private Integer userId;

	/** 好友id */
	private Integer friendId;

	/**
	 * 构造方法
	 */
	public FriendRelPK() {
		
	}
	
	
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the friendId
	 */
	public Integer getFriendId() {
		return friendId;
	}

	/**
	 * @param friendId the friendId to set
	 */
	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}
	
	
	
}
