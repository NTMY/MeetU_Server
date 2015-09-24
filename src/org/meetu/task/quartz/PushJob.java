package org.meetu.task.quartz;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meetu.util.TimeUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Quartz2定时推送实现类<br>
 * 采用侵入式设计<br>
 * */
public class PushJob extends QuartzJobBean {

	Log logger = LogFactory.getLog(PushJob.class);
	
	int timeout = 0;
	
	public void setTimeout(int i) {
		this.timeout = i;
	}
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		
		logger.warn("Quartz2定时任务执行中 ... " + TimeUtil.parseDate2Str(new Date()));
		
	}

}
