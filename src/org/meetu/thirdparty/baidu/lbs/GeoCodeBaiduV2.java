package org.meetu.thirdparty.baidu.lbs;

import static org.meetu.constant.Constant.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.meetu.action.UserAction;
import org.meetu.client.util.HttpUtil;

/**
 * 基于百度LBS的Geocode V2工具类
 * */
public class GeoCodeBaiduV2 {
	
	private static Log logger = LogFactory.getLog(GeoCodeBaiduV2.class);
	
	/**
	 * 逆向geocode<br>
	 * 通过经纬度获取地址信息 http://api.map.baidu.com/geocoder/v2/?ak=
	 * F56034a118b5fedfc66f6e39498b9c2e
	 * &location=39.983424,116.322987&output=xml&pois=1
	 * 
	 * @param lat纬度
	 * @param lng经度
	 * */
	public static String geocodeReverse(String lat, String lng) {
		long start = System.currentTimeMillis();// 起始时间
		StringBuffer urlSB = new StringBuffer(BAIDU_LBS_URL_PRE
				+ BAIDU_LBS_URL_SUFF);
		urlSB.append(lat).append(",").append(lng);
		logger.info(urlSB);
		String xml = HttpUtil.sendPost(urlSB.toString(), "");
		logger.info(xml);
		return xml;
	}

	// 39.983424, 116.322987
	//39.589332 115.9019
	public static void main(String[] args) throws Exception {
		String xml = geocodeReverse("39.892583", "116.396668");
		 jdom(xml);
//		dom4j(xml);
	}

	/**
	 * dom4j方式解析字段
	 * */
	static void dom4j(String xml) throws DocumentException,
			UnsupportedEncodingException {
		SAXReader reader = new SAXReader();
		org.dom4j.Document doc = reader.read(new ByteArrayInputStream(xml.getBytes("UTF8")));
		org.dom4j.Element root = doc.getRootElement();
		org.dom4j.XPath xpath;
	}

	/**
	 * jdom方式解析字段
	 * */
	static void jdom(String xml) throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		org.jdom.Document doc = builder.build(new ByteArrayInputStream(xml.getBytes("UTF8")));
		org.jdom.Element root = doc.getRootElement();
		// 获取formatted_address
		org.jdom.xpath.XPath xpath = org.jdom.xpath.XPath.newInstance("/GeocoderSearchResponse/result/formatted_address");
		org.jdom.Element e = (Element) xpath.selectSingleNode(root);
		logger.info(e.getTextTrim());
		// 获取business商圈信息
		xpath = org.jdom.xpath.XPath.newInstance("/GeocoderSearchResponse/result/business");
		e = (Element) xpath.selectSingleNode(root);
		logger.info(e.getTextTrim());
	}

}
