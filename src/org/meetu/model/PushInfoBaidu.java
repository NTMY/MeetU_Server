package org.meetu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 百度推送用户数据<br>
 * 百度推送的TAG暂时不使用,不再单独建表存储了
 * */
@Entity
@Table(name = "push_info_baidu", catalog = "meetu")
public class PushInfoBaidu extends BaseModel {

	private static final long serialVersionUID = 4083756193715742783L;

	/**
	 * 设备硬件IMEI
	 * PK
	 * */
	@Id
	@Column(name = "imei")
	private String imei;
	
	/**
	 * meetu系统中的userId<br>
	 * */
	@Column(name = "userId")
	private Integer userId;

	/**
	 * 百度推送方硬件标识
	 * */
	@Column(name = "channelId", nullable = false)
	private String channelId;

	/**
	 * 构造方法
	 * */
	public PushInfoBaidu() {

	}

	/**
	 * 构造方法
	 * */
	public PushInfoBaidu(int userId, String channelId) {
		super();
		this.userId = userId;
		this.channelId = channelId;
	}

	/**
	 * @return the channelId
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * @param channelId
	 *            the channelId to set
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
