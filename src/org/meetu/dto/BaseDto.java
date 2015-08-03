package org.meetu.dto;

/**
 * DTO基类
 * */
public class BaseDto {
	
	/**
	 * 错误码
	 * 如果错误码不为空,则不用识别其子类属性
	 * */
	private String errCode;
	
	/**
	 * 错误描述
	 * */
	private String errMsg;
	
	
	public BaseDto() {
	}
	
	
	public BaseDto(String errCode,String errMsg) {
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
