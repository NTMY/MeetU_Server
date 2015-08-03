package org.meetu.model;

import java.io.Serializable;

/**
 * Model层基类,Model层实体类都继承此类
 * */
public class BaseModel implements Serializable {
	/**
	 * 错误码
	 * 如果错误码不为空,则不用识别其子类属性
	 * */
	private String errCode;
	
	/**
	 * 错误描述
	 * */
	private String errMsg;
	
	
	public BaseModel() {
	}
	
	
	public BaseModel(String errCode,String errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}


	public String getErrMsg() {
		return errMsg;
	}


	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	
}
