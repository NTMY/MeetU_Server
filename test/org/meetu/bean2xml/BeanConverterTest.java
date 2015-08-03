package org.meetu.bean2xml;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.meetu.model.User;
import org.meetu.util.BeanConverter;
import org.meetu.util.ListBean;

import com.thoughtworks.xstream.XStream;

public class BeanConverterTest {
	@Test
	public void testBean2Xml() {
		System.out.println("bean ------------ xml");
		User bean = new User();
		bean.setEmail("jack@email.com");
		bean.setId(1);
		bean.setName("jack");
		String xml = BeanConverter.bean2xml(bean);
		System.out.println(xml);
	}

	@Test
	public void testBeansListXml() {
		System.out.println("bean list ------------- xml");
		User bean = new User();
		bean.setEmail("jack@email.com");
		bean.setId(1);
		bean.setName("jack");
		User bean2 = new User();
		bean2.setEmail("peter@email.com");
		bean2.setId(2);
		bean2.setName("peter");
		List list = new ArrayList();
		list.add(bean);
		list.add(bean2);
		String xml = BeanConverter.bean2xml(list);
		System.out.println(xml);
	}

	
	@Test
	public void test() {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?><org.meetu.util.ListBean>  <list/></org.meetu.util.ListBean>";
		
		BeanConverter.xmlToBean(xml);
		
		
	}
}
