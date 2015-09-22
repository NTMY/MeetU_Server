package org.meetu.model;


/**
 * App版本信息MODEL类
 * */
public class AppVer extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2936479865303334917L;

	/**
	 * 自增主键
	 * */
	private int id;
	
	/**
	 * 操作系统名称
	 * */
	private String os;
	
	/**
	 * app版本
	 * */
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
	 * 下载链接
	 * */
	private String downloadUrl;
	
	
	/**
	 * 构造方法
	 * */
	public AppVer() {
		
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


	/**
	 * @return the isTop
	 */
	public boolean getIsTop() {
		return isTop;
	}


	/**
	 * @param isTop the isTop to set
	 */
	public void setIsTop(boolean isTop) {
		this.isTop = isTop;
	}


	/**
	 * @return the downloadUrl
	 */
	public String getDownloadUrl() {
		return downloadUrl;
	}


	/**
	 * @param downloadUrl the downloadUrl to set
	 */
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the os
	 */
	public String getOs() {
		return os;
	}


	/**
	 * @param os the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}


	/**
	 * @return the appVer
	 */
	public String getAppVer() {
		return appVer;
	}


	/**
	 * @param appVer the appVer to set
	 */
	public void setAppVer(String appVer) {
		this.appVer = appVer;
	}


	/**
	 * @param isTop the isTop to set
	 */
	public void setTop(boolean isTop) {
		this.isTop = isTop;
	}



	
	
	
}
