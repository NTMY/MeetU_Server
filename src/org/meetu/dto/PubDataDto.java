package org.meetu.dto;

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
	
	
	
	
}
