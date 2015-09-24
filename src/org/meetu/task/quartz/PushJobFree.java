package org.meetu.task.quartz;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.meetu.util.TimeUtil;

/**
 * Quartz2定时推送实现类<br>
 * 采用非侵入式设计<br>
 * */
public class PushJobFree {

	Log logger = LogFactory.getLog(PushJobFree.class);
	
	public void exePush() {
		logger.warn("---- 非侵入式quartz任务执行...... " + TimeUtil.parseDate2Str(new Date()));
	}
	
}
