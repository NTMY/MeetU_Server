package org.meetu.client.test;

import org.meetu.client.handler.FeedbackHandler;
import org.meetu.client.listener.impl.FeedbackListenerImpl;
import org.meetu.model.Feedback;

public class FeedbackTest {
	public static void main(String[] args) {
		FeedbackHandler handler = new FeedbackHandler();
		Feedback feed = new Feedback();
		feed.setUserId(1);
		feed.setContent("1");
		handler.onFeedback(new FeedbackListenerImpl(), feed);
	}
}
