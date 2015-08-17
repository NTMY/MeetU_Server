package com.baidu.yun.push.sample;

import java.util.List;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.QueryTopicListRequest;
import com.baidu.yun.push.model.QueryTopicListResponse;
import com.baidu.yun.push.model.TopicResultInfo;

public class QueryTopicList {
	public static void main(String[] args)
			throws PushClientException,PushServerException {
		// 1. get apiKey and secretKey from developer console
		String apiKey = "xxxxxxxxxxxxxxxxxxxx";
		String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

		// 2. build a BaidupushClient object to access released interfaces
		BaiduPushClient pushClient = new BaiduPushClient(pair,
				BaiduPushConstants.CHANNEL_REST_URL);

		// 3. register a YunLogHandler to get detail interacting information
		// in this request.
		pushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			// 4. specify request arguments
			QueryTopicListRequest request = new QueryTopicListRequest()
					.addStart(0).addLimit(6).addDeviceType(3);
			// 5. http request
			QueryTopicListResponse response = pushClient
					.queryTopicList(request);
			// Http请求结果解析打印
			System.out.println("totalNum: " + response.getTotalNum() + "\n"
					+ "result:");
			if (null != response) {
				List<?> list = response.getTimerResultInfos();
				for (int i = 0; i < list.size(); i++) {
					Object object = list.get(i);
					StringBuilder strBuilder = new StringBuilder();
					if (object instanceof TopicResultInfo) {
						TopicResultInfo topicResult = (TopicResultInfo) object;
						strBuilder.append("List[" + i + "]: " + "topicId= "
								+ topicResult.getTopicId() + ",firstPushTime= "
								+ topicResult.getFirstPushTime()
								+ ",lastPushTime= "
								+ topicResult.getLastPushTime()
								+ ",totalPushDevsNum= "
								+ topicResult.getTotalPushDevsNum()
								+ ",totalAckDevsNum= "
								+ topicResult.getTotalAckDevsNum());
					}
					System.out.println(strBuilder.toString());
				}
			}
		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				System.out.println(String.format(
						"requestId: %d, errorCode: %d, errorMessage: %s",
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}
	}
}
