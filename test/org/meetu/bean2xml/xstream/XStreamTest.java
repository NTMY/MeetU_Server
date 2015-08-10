package org.meetu.bean2xml.xstream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.meetu.model.User;
import org.meetu.util.ListBean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;
 
/**
 * <b>function:</b>Java对象和XML字符串的相互转换
 * jar-lib-version: xstream-1.3.1
 * @author hoojo
 * @createDate Nov 27, 2010 12:15:15 PM
 * @file XStreamTest.java
 * @package com.hoo.test
 * @project WebHttpUtils
 * @blog http://blog.csdn.net/IBM_hoojo
 * @email hoojo_@126.com
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class XStreamTest {
    
    private XStream xstream = null;
    private ObjectOutputStream  out = null;
    private ObjectInputStream in = null;
    
    private User bean = null;
    
    /**
     * <b>function:</b>初始化资源准备
     * @author hoojo
     * @createDate Nov 27, 2010 12:16:28 PM
     */
//    @Before
    public void init() {
        try {
            xstream = new XStream();
            //xstream = new XStream(new DomDriver()); // 需要xpp3 jar
        } catch (Exception e) {
            e.printStackTrace();
        }
        bean = new User();
        bean.setId(1);
        bean.setName("jack");
    }
    
    /**
     * <b>function:</b>释放对象资源
     * @author hoojo
     * @createDate Nov 27, 2010 12:16:38 PM
     */
//    @After
    public void destory() {
        xstream = null;
        bean = null;
        try {
            if (out != null) {
                out.flush();
                out.close();
            }
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.gc();
    }
    
    public final void fail(String string) {
        System.out.println(string);
    }
    
    public final void failRed(String string) {
        System.err.println(string);
    }
    
    
    
    
    
    
    
    
    /**
     * <b>function:</b>Java对象转换成XML字符串
     * @author hoojo
     * @createDate Nov 27, 2010 12:19:01 PM
     */
//    @Test
    public void writeBean2XML() {
        try {
            fail("------------Bean->XML------------");
            fail(xstream.toXML(bean));
            fail("重命名后的XML");
            //类重命名
            //xstream.alias("account", Student.class);
            //xstream.alias("生日", Birthday.class);
            //xstream.aliasField("生日", Student.class, "birthday");
            //xstream.aliasField("生日", Birthday.class, "birthday");
            //fail(xstream.toXML(bean));
            //属性重命名
            xstream.aliasField("邮件", User.class, "email");
            //包重命名
            xstream.aliasPackage("hoo", "com.hoo.entity");
            fail(xstream.toXML(bean));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    /**
     * <b>function:</b>将Java的List集合转换成XML对象
     * @author hoojo
     * @createDate Nov 27, 2010 12:20:07 PM
     */
//    @Test
    public void writeList2XML() {
        try {
            //修改元素名称
            xstream.alias("beans", ListBean.class);
            xstream.alias("user", User.class);
            fail("----------List-->XML----------");
            ListBean listBean = new ListBean();
            listBean.setName("this is a List Collection");
            
            List<Object> list = new ArrayList<Object>();
            list.add(bean);
            list.add(bean);//引用bean
            //list.add(listBean);//引用listBean，父元素
            
            bean = new User();
            bean.setEmail("tom@125.com");
            bean.setId(2);
            bean.setName("tom");
            list.add(bean);
            listBean.setList(list);
            
            //将ListBean中的集合设置空元素，即不显示集合元素标签
            //xstream.addImplicitCollection(ListBean.class, "list");
            
            //设置reference模型
            //xstream.setMode(XStream.NO_REFERENCES);//不引用
            xstream.setMode(XStream.ID_REFERENCES);//id引用
            //xstream.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);//绝对路径引用
              
            //将name设置为父类（Student）的元素的属性
            xstream.useAttributeFor(User.class, "name");
            //修改属性的name
            xstream.aliasAttribute("姓名", "name");
          
            fail(xstream.toXML(listBean));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    
    
    
}