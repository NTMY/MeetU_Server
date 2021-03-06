package org.meetu.push.baidu;

import org.meetu.dto.PushBaiduParam;

import net.sf.json.JSONObject;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import org.meetu.push.baidu.base.PushBaiduDef;

import static org.meetu.constant.Constant.*;

/**
 * 推送给单个客户端
 * */
public class AndroidPushMsgToSingleDevice extends PushBaiduDef {
	public static void push(PushBaiduParam p) throws PushClientException,
			PushServerException {
		// 1. get apiKey and secretKey from developer console

		// 2. build a BaidupushClient object to access released interfaces
		BaiduPushClient pushClient = new BaiduPushClient(pair,
				BaiduPushConstants.CHANNEL_REST_URL);

		// 3. register a YunLogHandler to get detail interacting information
		// in this request.
		pushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				logger.info(event.getMessage());
			}
		});

		try {
			// 4. specify request arguments
			// 创建 Android的通知
			JSONObject notification = new JSONObject();
			notification.put("title", p.getTitle());
			notification.put("description", p.getDesc());
			notification.put("notification_builder_id", 0);
			notification.put("notification_basic_style", 4);
			notification.put("open_type", 1);
			notification.put("url", "http://push.baidu.com");
			JSONObject jsonCustormCont = new JSONObject();
			jsonCustormCont.put("key", "value"); // 自定义内容，key-value
			notification.put("custom_content", jsonCustormCont);

			PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
					// 设置channelId
					.addChannelId(p.getChannelId())
					// message有效时间
					.addMsgExpires(new Integer(3600))
					// 1：通知,0:透传消息. 默认为0 注：IOS只有通知.
					.addMessageType(p.getType())
					.addMessage(notification.toString())
					// deviceType => 3:android, 4:ios
					.addDeviceType(3);
			// 5. http request
			PushMsgToSingleDeviceResponse response = pushClient
					.pushMsgToSingleDevice(request);
			// Http请求结果解析打印
			logger.info("msgId: " + response.getMsgId() + ",sendTime: "
					+ response.getSendTime());
		} catch (PushClientException e) {
			/*
			 * ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,'true' 表示抛出, 'false' 表示捕获。
			 */
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				logger.info(String.format(
						"requestId: %d, errorCode: %d, errorMessage: %s",
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}
	}
	
}
