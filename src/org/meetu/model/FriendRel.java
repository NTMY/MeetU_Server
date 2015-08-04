package org.meetu.model;

import java.util.Date;

/**
 * 好友关系实体类
 * 
drop table if exists u_friends_rel;
create table u_friends_rel (
	userId int(16) not null comment '用户id',
	friendId int(16) not null comment '朋友id',
	relStatus varchar(4) not null comment '朋友状态0:正常 1:亲密状态 2:黑名单 3:已删除',
	happenTime timestamp comment '成为朋友的时间'
) comment '用户好友关系表'; 
 * */
public class FriendRel extends BaseModel {

	private static final long serialVersionUID = -7425004151481819899L;

	/** 用户id */
	private Integer userId;

	/** 好友id */
	private Integer friendId;

	/** 好友状态 0:正常 1:黑名单 2:已删除 */
	private String statusRel;

	/** 成为好友的时间 */
	private Date happenTime;

	/**
	 * 构造方法constructor
	 * */
	public FriendRel() {

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
	 * @return the friendId
	 */
	public Integer getFriendId() {
		return friendId;
	}

	/**
	 * @param friendId
	 *            the friendId to set
	 */
	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}

	/**
	 * @return the happenTime
	 */
	public Date getHappenTime() {
		return happenTime;
	}

	/**
	 * @param happenTime
	 *            the happenTime to set
	 */
	public void setHappenTime(Date happenTime) {
		this.happenTime = happenTime;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the statusRel
	 */
	public String getStatusRel() {
		return statusRel;
	}

	/**
	 * @param statusRel
	 *            the statusRel to set
	 */
	public void setStatusRel(String statusRel) {
		this.statusRel = statusRel;
	}

}
