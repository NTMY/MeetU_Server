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
	 * meetu系统中的userId<br>
	 * 表主键
	 * */
	@Id
	private int userId;

	/**
	 * 百度推送方userId
	 * */
	@Column(name = "userId_push", nullable = false)
	private String userId_push;

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
	public PushInfoBaidu(int userId, String userId_push, String channelId) {
		super();
		this.userId = userId;
		this.userId_push = userId_push;
		this.channelId = channelId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the userId_push
	 */
	public String getUserId_push() {
		return userId_push;
	}

	/**
	 * @param userId_push
	 *            the userId_push to set
	 */
	public void setUserId_push(String userId_push) {
		this.userId_push = userId_push;
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

}
