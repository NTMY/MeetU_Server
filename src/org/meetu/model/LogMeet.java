package org.meetu.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 相遇记录,每次相遇都会记录 持久化实体类
 * */
@Entity
@Table(name = "log_meet", catalog = "meetu")
public class LogMeet extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 自动生成自增主键 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)    
	@Column(name = "id")
	private Integer id;

	/**
	 * 主动用户数据
	 * */
	@Column(name = "userId")
	private Integer userId;

	/** 主动用户经度 */
	@Column(name = "user_longitude")
	private Double userLongitude;

	/** 主动用户纬度 */
	@Column(name = "user_latitude")
	private Double userLatitude;

	/** 主动用户地址描述 */
	@Column(name = "user_address")
	private String userAddress;

	/**
	 * 被动用户数据
	 * */
	@Column(name = "friendId")
	private Integer friendId;

	/** 被动用户经度 */
	@Column(name = "friend_longitude")
	private Double friendLongitude;

	/** 被动用户纬度 */
	@Column(name = "friend_latitude")
	private Double friendLatitude;

	/** 被动用户用户地址描述 */
	@Column(name = "friend_address")
	private String friendAddress;

	/** 相遇时间 */
	@Column(name = "happenTime")
	private Timestamp happenTime;

	/** constructor */
	public LogMeet() {

	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	public Timestamp getHappenTime() {
		return happenTime;
	}

	/**
	 * @param happenTime
	 *            the happenTime to set
	 */
	public void setHappenTime(Timestamp happenTime) {
		this.happenTime = happenTime;
	}

	/**
	 * @return the userLongitude
	 */
	public Double getUserLongitude() {
		return userLongitude;
	}

	/**
	 * @param userLongitude
	 *            the userLongitude to set
	 */
	public void setUserLongitude(Double userLongitude) {
		this.userLongitude = userLongitude;
	}

	/**
	 * @return the userLatitude
	 */
	public Double getUserLatitude() {
		return userLatitude;
	}

	/**
	 * @param userLatitude
	 *            the userLatitude to set
	 */
	public void setUserLatitude(Double userLatitude) {
		this.userLatitude = userLatitude;
	}

	/**
	 * @return the userAddress
	 */
	public String getUserAddress() {
		return userAddress;
	}

	/**
	 * @param userAddress
	 *            the userAddress to set
	 */
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	/**
	 * @return the friendLongitude
	 */
	public Double getFriendLongitude() {
		return friendLongitude;
	}

	/**
	 * @param friendLongitude
	 *            the friendLongitude to set
	 */
	public void setFriendLongitude(Double friendLongitude) {
		this.friendLongitude = friendLongitude;
	}

	/**
	 * @return the friendLatitude
	 */
	public Double getFriendLatitude() {
		return friendLatitude;
	}

	/**
	 * @param friendLatitude
	 *            the friendLatitude to set
	 */
	public void setFriendLatitude(Double friendLatitude) {
		this.friendLatitude = friendLatitude;
	}

	/**
	 * @return the friendAddress
	 */
	public String getFriendAddress() {
		return friendAddress;
	}

	/**
	 * @param friendAddress
	 *            the friendAddress to set
	 */
	public void setFriendAddress(String friendAddress) {
		this.friendAddress = friendAddress;
	}

}
