package org.meetu.model;

/**
 * 上传头像实体类<br>
 * 封装上传的数据<br>
 * 并不持久化此对象
 * */
public class PortraitUploadModel {
	
	/**
	 * 用户id
	 * */
	private String userId;
	
	/**
	 * 清晰度<br>
	 * 高清为"HD",其余均为非高清
	 * */
	private String resolution;
	
	
	public PortraitUploadModel() {
		
	}


	
	/**
	 * getters and setters
	 * */
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getResolution() {
		return resolution;
	}


	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	
	
	
}
