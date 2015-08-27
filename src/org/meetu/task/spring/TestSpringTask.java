package org.meetu.task.spring;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meetu.util.TimeUtil;
import org.springframework.stereotype.Service;


/**
 * 
 * SpringTask测试类
 * 
 * */
@Service
public class TestSpringTask {
	Log logger = LogFactory.getLog(TestSpringTask.class);
	/**
	 * 构造方法
	 * */
	public TestSpringTask() {
		logger.warn("TestSpringTask创建了 --- " + TimeUtil.parseDate2Str(new Date()));
	}
	
	public void job1() {
		logger.warn("==============================SpringTask job1 execute START==============================");
		logger.warn("==============================SpringTask job1 execute END==============================");
	}
	
}
