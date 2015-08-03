package org.meetu.dto;

import org.meetu.model.User;

/**
 * UserAction!access接口中,返回值封装对象
 * */
public class UserAccessDto extends BaseDto {

	/**
	 * REG/LOGIN
	 * */
	private String access_status;
	
	/**
	 * 用户主键
	 * */
	private User user;

	
	
	public UserAccessDto() {
		
	}
	
	public UserAccessDto(String access_status, User user) {
		super();
		this.access_status = access_status;
		this.user = user;
	}
	
	
	/****************************
	 * getters and setters 
	 * **************************/
	public String getAccess_status() {
		return access_status;
	}

	public void setAccess_status(String access_status) {
		this.access_status = access_status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
