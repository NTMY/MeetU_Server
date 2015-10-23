package org.meetu.model;

import java.util.Date;

/**
 * 用户地址当前表
 * */
//@XStreamAlias("locationCurr")
public class LocationCurr extends BaseModel {

	/** 用户ID */
	private Integer userId;
	
	/** 经度 */
	private Double longitude;

	/** 纬度 */
	private Double latitude;
	
	/** 地址信息描述 */
	private String address;

	/** 附近商圈 */
	private String business;
	
	/** 上传时间 */
	private Date uploadTime;

	/**
	 * 关联
	 * */
	private User user;
	
	/**
	 * 
	 * 查询条件
	 * 
	 * */
	/**
	 * 经度最小值
	 * */
	private Double minLong;

	/**
	 * 经度最大值
	 * */
	private Double maxLong;

	/**
	 * 纬度最小值
	 * */
	private Double minLat;

	/**
	 * 纬度度最大值
	 * */
	private Double maxLat;

	
	/**
	 * 关联
	 * */
	private User u;
	
	/**
	 * constructor
	 * */
	public LocationCurr() {

	}

	/**
	 * constructor
	 * */
	public LocationCurr(int userId, double longitude, double latitude,
			Date uploadTime) {
		super();
		this.userId = userId;
		this.longitude = longitude;
		this.latitude = latitude;
		this.uploadTime = uploadTime;
	}
	
	/**
	 * 设置range,4个边界值arr[minLat, minLng, maxLat, maxLng]
	 * */
	public void setRange(double[] range) {
		this.minLat = range[0];
		this.minLong = range[1];
		this.maxLat = range[2];
		this.maxLong = range[3];
	}

	/**********************************************************************
	 * 
	 * getters and setters
	 * 
	 * ********************************************************************/

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Double getMinLong() {
		return minLong;
	}

	public void setMinLong(Double minLong) {
		this.minLong = minLong;
	}

	public Double getMaxLong() {
		return maxLong;
	}

	public void setMaxLong(Double maxLong) {
		this.maxLong = maxLong;
	}

	public Double getMinLat() {
		return minLat;
	}

	public void setMinLat(Double minLat) {
		this.minLat = minLat;
	}

	public Double getMaxLat() {
		return maxLat;
	}

	public void setMaxLat(Double maxLat) {
		this.maxLat = maxLat;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
}
