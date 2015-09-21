package org.meetu.client.handler;

import org.meetu.client.listener.FeedbackListener;
import org.meetu.dto.BaseDto;
import org.meetu.model.Feedback;
import org.meetu.util.BeanConverter;

/**
 * 处理用户反馈信息<br>
 * 由客户端调用
 * */
public class FeedbackHandler extends BaseHandler {
	
	/**
	 * 用户提交反馈信息
	 * */
	public void onFeedback(FeedbackListener listener,Feedback feed) {
		String subUrl = "/feedbackAction!feedback?";
		StringBuffer param = new StringBuffer();
		param.append("feed.userId=").append(feed.getUserId()).append("&feed.content=").append(feed.getContent());
		xml = send(subUrl, param.toString());
		BaseDto dto = (BaseDto) BeanConverter.xmlToBean(xml);
		if (listener != null) {
			listener.feedback(dto);
		}

	}
	
}
