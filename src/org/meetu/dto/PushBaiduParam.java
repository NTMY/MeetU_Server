package org.meetu.dto;

/**
 * 封装推送参数
 * */
public class PushBaiduParam {
	
	/**
	 * 绑定的channelId
	 * */
	private String channelId;
	
	/**
	 * 推送标题
	 * */
	private String title;
	
	/**
	 * 推送详细内容
	 * */
	private String desc;

	/**
	 * 类型 0:透传消息 1:推送通知
	 * */
	private int type;
	
	/**deviceType设备类型
	 * 3:android 4:ios*/
	private int deviceType;
	
	
	
	
	
	/**
	 * @return the channelId
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * @param channelId the channelId to set
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the deviceType
	 */
	public int getDeviceType() {
		return deviceType;
	}

	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}
	
	
}
