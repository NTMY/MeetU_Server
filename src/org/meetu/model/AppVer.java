package org.meetu.model;

import org.meetu.model.key.AppVerPK;

/**
 * App版本信息MODEL类
 * */
public class AppVer extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2936479865303334917L;

	/**
	 * 主键对象
	 * */
	private AppVerPK pk;
	
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
	public AppVer() {
		
	}
	
	
	public AppVerPK getPk() {
		return pk;
	}

	public void setPk(AppVerPK pk) {
		this.pk = pk;
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


	public boolean isTop() {
		return isTop;
	}


	public void setTop(boolean isTop) {
		this.isTop = isTop;
	}
	
	
	
	
}
