package org.meetu.baidu.lbs;

import org.meetu.client.util.HttpUtil;

import static org.meetu.constant.Constant.*;

public class GeoCoderTest {
	public static void main(String[] args) {
		String url = BAIDU_LBS_URL_PRE;
		String param = "ak=" + BAIDU_SN
				+ "&location=39.983424,116.322987&output=xml&pois=0";
		String param1 = "ak=F56034a118b5fedfc66f6e39498b9c2e&location=39.983424,116.322987&output=xml&pois=1";
		String url1 = "http://api.map.baidu.com/geocoder/v2/?ak=F56034a118b5fedfc66f6e39498b9c2e&location=39.983424,116.322987&output=xml&pois=1";
		String xml = HttpUtil.sendPost(url, param1);
		
		String xml1 = HttpUtil.sendPost(url1, "");
		System.out.println(xml);
		System.out.println(xml1);
	}
}
