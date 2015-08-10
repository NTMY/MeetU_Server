package org.meetu.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Model层基类,Model层实体类都继承此类
 * */
public class BaseModel implements Serializable,Cloneable {
	
	/**
	 * logger
	 * */
	Log logger = LogFactory.getLog(getClass());
	
	/**
	 * 错误码
	 * 如果错误码不为空,则不用识别其子类属性
	 * */
	private String errCode;
	
	/**
	 * 错误描述
	 * */
	private String errMsg;
	
	/**
	 * 构造方法
	 * */
	public BaseModel() {
	}
	
	/**
	 * 构造方法
	 * */
	public BaseModel(String errCode,String errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	/**
	 * 
	 * */
	public void copyFrom(BaseModel origin) {
		try {
			BeanUtils.copyProperties(this, origin);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error(e);
		}
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
