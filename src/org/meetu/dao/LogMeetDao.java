package org.meetu.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.meetu.model.LogMeet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 相遇记录日志dao
 * */
@Repository
public class LogMeetDao {
	private static final Log logger = LogFactory.getLog(LogMeetDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void insert(LogMeet log) {
		Session session = sessionFactory.getCurrentSession();
		Object obj = session.save(log);
		logger.info("insert return object = " + obj.getClass() + " ===== " + obj);
	}
	
	
	
}
