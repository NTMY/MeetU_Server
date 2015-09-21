package org.meetu.model.key;

import java.io.Serializable;

/**
 * AppVer的在主键
 * */
public class AppVerPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6079602542310170697L;

	/**
	 * 操作系统名ANDROID/IOS
	 * */
	private String OS;
	
	/**
	 * app软件版本
	 * */
	private String appVer;

	
	public String getOS() {
		return OS;
	}

	public void setOS(String oS) {
		OS = oS;
	}

	public String getAppVer() {
		return appVer;
	}

	public void setAppVer(String appVer) {
		this.appVer = appVer;
	}
	
	
	
	
}
