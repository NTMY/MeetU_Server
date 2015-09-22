package org.meetu.dto;

import org.meetu.model.AppVer;

/**
 * 公共数据实体类
 * */
public class PubDataDto extends BaseDto  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6852617132140288843L;

	/** upload接口调用,上传间隔(秒) */
	private String uploadFreq;
	
	/** meetu接口调用,查找间隔(秒) */
	private String meetuFreq;
	
	/** 建议更新等级 0:无更新 1:建议更新 2:强制更新 */
	private String updateLv;

	/**下载地址*/
	private String downloadUrl;
	
	/**版本描述*/
	private String desc;
	
	public PubDataDto() {
		
	}
	public PubDataDto(String errCode , String errMsg) {
		super(errCode,errMsg);
	}

	/**
	 * @return the uploadFreq
	 */
	public String getUploadFreq() {
		return uploadFreq;
	}


	/**
	 * @param uploadFreq the uploadFreq to set
	 */
	public void setUploadFreq(String uploadFreq) {
		this.uploadFreq = uploadFreq;
	}


	/**
	 * @return the meetuFreq
	 */
	public String getMeetuFreq() {
		return meetuFreq;
	}


	/**
	 * @param meetuFreq the meetuFreq to set
	 */
	public void setMeetuFreq(String meetuFreq) {
		this.meetuFreq = meetuFreq;
	}
	/**
	 * @return the updateLv
	 */
	public String getUpdateLv() {
		return updateLv;
	}
	/**
	 * @param updateLv the updateLv to set
	 */
	public void setUpdateLv(String updateLv) {
		this.updateLv = updateLv;
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
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
	
}
