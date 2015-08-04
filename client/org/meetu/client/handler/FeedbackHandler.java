package org.meetu.client.handler;

import static org.meetu.client.util.HttpUtil.sendPost;
import static org.meetu.constant.Constant.URL;




import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meetu.client.listener.FeedbackListener;
import org.meetu.dto.BaseDto;
import org.meetu.model.Feedback;
import org.meetu.util.BeanConverter;

/**
 * 处理用户反馈信息<br>
 * 由客户端调用
 * */
public class FeedbackHandler {
	
	Log logger = LogFactory.getLog(FeedbackHandler.class);
	/**
	 * 用户提交反馈信息
	 * */
	public void onFeedback(FeedbackListener listener,Feedback feed) {
		String subUrl = "/feedbackAction!feedback?";
		StringBuffer param = new StringBuffer();
		param.append("feed.userId=").append(feed.getUserId()).append("&feed.content=").append(feed.getContent());
		String xml = sendPost(URL + subUrl, param.toString());
		logger.info("xml == " + xml);
		BaseDto dto = (BaseDto) BeanConverter.xmlToBean(xml);
		if (listener != null) {
			listener.feedback(dto);
		}

	}
	
}
