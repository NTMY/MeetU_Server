package org.meetu.model;

import org.meetu.model.key.AppVerPK;

/**
 * 公共报文Model,只负责供客户端上传参数使用,并不持久化此对象
 * */
public class PubData {

	/**
	 * AppVer对象<br>
	 * 放弃使用PubData包含AppVer对象,改为包含AppVer类所有属性,方便日后扩展
	 * */
	//private AppVer appVer;
	
	
	/**
	 * App主键(os,appVer)
	 * */
	private String os;
	
	private String appVer;
	
	/**
	 * 客户端生成签名串
	 * */
	private String signature;
	
	/**
	 * 版本描述
	 * */
	private String description;

	/**
	 * 是否为最新
	 * */
	private boolean isTop;
	
	/**
	 * 构造方法
	 * */
	public PubData() {
		
	}
	
	
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getOs() {
		return os;
	}


	public void setOs(String os) {
		this.os = os;
	}


	public String getAppVer() {
		return appVer;
	}


	public void setAppVer(String appVer) {
		this.appVer = appVer;
	}


	public boolean isTop() {
		return isTop;
	}


	public void setTop(boolean isTop) {
		this.isTop = isTop;
	}
	
	
}
