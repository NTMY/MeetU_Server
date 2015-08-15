package org.meetu.client.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meetu.model.User;
import org.meetu.util.SecureUtil;

import static org.meetu.constant.Constant.*;

/**
 * HTTP通信工具类
 * */
public class HttpUtil {
	private static Log logger = LogFactory.getLog(HttpUtil.class);

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;

		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			conn.setConnectTimeout(TIMEOUT_HTTP);// 设置连接超时时间
			conn.setReadTimeout(TIMEOUT_HTTP);// 设置read超时时间
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 如果参数是String
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}

		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			logger.error(e);
		} finally {
			// 使用finally块来关闭输出流、输入流
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				logger.error(ex);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// String xml = sendPost(
		// "http://45.55.4.64:8080/meetu/userAction!access?",
		// "user.mobile=13911592475&user.pwd=abc");

		User user = new User();
		user.setId(0);
		user.setName("高文");
		String str = "";
		String xmlObj = sendPost(
				"http://localhost:8080/meetu/userAction!update?",
				str);
		System.out.println(xmlObj);
	}
}
