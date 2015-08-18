package org.meetu.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.meetu.model.PushInfoBaidu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * baidu推送用户数据
 * */
@Repository
public class PushBaiduDao {
	private static final Log log = LogFactory.getLog(PushBaiduDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void insertOrUpdate(PushInfoBaidu push) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(push);
		session.getTransaction().commit();
		session.close();
	}
	
}
