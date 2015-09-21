package org.meetu.client.test;

import org.meetu.client.handler.FeedbackHandler;
import org.meetu.client.listener.impl.FeedbackListenerImpl;
import org.meetu.model.Feedback;

public class FeedbackTest {
	public static void main(String[] args) {
		FeedbackHandler handler = new FeedbackHandler();
		Feedback feed = new Feedback();
		feed.setUserId(1);
		feed.setContent("123abc中文中文中文中文中文abc123!@#$%^&");
		handler.onFeedback(new FeedbackListenerImpl(), feed);
	}
}
