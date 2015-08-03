package org.meetu.model;

import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 用户地址当前表
 * */
@XStreamAlias("locationHis")
public class LocationHis extends BaseModel {
	/** 历史数据id auto_increment */
	private Integer id;

	/** 用户设备IMEI */
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
	 * constructor
	 * */
	public LocationHis() {

	}

	/**
	 * constructor
	 * */
	public LocationHis(Integer id, Integer userId, double longitude,
			double latitude, Date uploadTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.longitude = longitude;
		this.latitude = latitude;
		this.uploadTime = uploadTime;
	}

	/**
	 * constructor Curr当前地址对象转His历史地址对象
	 * */
	public LocationHis(LocationCurr curr) {
		this.userId = curr.getUserId();
		this.longitude = curr.getLongitude();
		this.latitude = curr.getLatitude();
		this.uploadTime = curr.getUploadTime();
		this.address = curr.getAddress();
		this.business = curr.getBusiness();
	}

	/**********************************************************************
	 * 
	 * getters and setters
	 * 
	 * ********************************************************************/
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
	
}
