package org.meetu.model;

import java.util.Date;

/**
 * 用户反馈信息实体类
 * */
public class Feedback extends BaseModel {

	private static final long serialVersionUID = 6911263509261298398L;

	/**反馈人用户id*/
	private Integer userId;
	
	/**反馈信息*/
	private String content;
	
	/**反馈时间*/
	private Date feedbackTime;

	/**
	 * 构造方法constructor
	 * */
	public Feedback() {
		
	}

	/**
	 * 构造方法constructor
	 * */
	public Feedback(Integer userId, String content, Date feedbackTime) {
		super();
		this.userId = userId;
		this.content = content;
		this.feedbackTime = feedbackTime;
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

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the feedbackTime
	 */
	public Date getFeedbackTime() {
		return feedbackTime;
	}

	/**
	 * @param feedbackTime the feedbackTime to set
	 */
	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}
	
	
	
}
