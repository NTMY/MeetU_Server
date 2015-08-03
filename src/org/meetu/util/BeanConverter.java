package org.meetu.util;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thoughtworks.xstream.XStream;

import static org.meetu.constant.Constant.*;



/**
 * 提供对象转xml,json等操作
 * */
public class BeanConverter {
	private static final Log logger = LogFactory.getLog(BeanConverter.class);
	
	private static XStream xstream = null;
	static {
		try {
			xstream = new XStream();
			xstream.autodetectAnnotations(true);
//			xstream.aliasSystemAttribute(null,"class");
//			xstream.registerConverter(new DateConverter("yyyy-MM-dd",null));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 单个bean转xml
	 * */
	public static String bean2xml(Object bean) {
		String xml = XML_HEAD;
		xml += xstream.toXML(bean);
		return xml;
	}

	/**
	 * list集合bean转xml
	 * 
	 * @param <T>
	 * */
	public static <T> String bean2xml(List<T> list) {
		String xml = XML_HEAD;
		ListBean beans = new ListBean();
		beans.setList(list);
		xml += xstream.toXML(beans);
		return xml;
	}
	
	/**
	 * ListBean转xml
	 * @param <T>
	 * */
	public static <T> String bean2xml(ListBean<T> beans) {
		String xml = XML_HEAD;
		xml += xstream.toXML(beans);
		return xml;
	}

	/**
	 * bean转json
	 * */
	public static String bean2json() {
		String json = "";
		return json;
	}

	/**
	 * xml转json
	 * */
	public static String xml2json(String xml) {
		String json = "";
		return json;
	}

	/**
	 * json转xml
	 * */
	public static String json2xml(String json) {
		String xml = "";
		return xml;
	}
	
	/**
	 * xml字符串转为Listbean
	 * @param xml 字符串
	 * @return 如果接收list数据,则转为ListBean,否则转为不同的实体类或者根据字段解析
	 * */
	public static Object xmlToBean(String xml) {
		Object obj =  xstream.fromXML(xml);
		return obj;
	}
}
