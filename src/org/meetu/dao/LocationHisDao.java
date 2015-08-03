package org.meetu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.meetu.model.LocationHis;

public class LocationHisDao {
	private static final Log log = LogFactory.getLog(LocationHisDao.class);

	private SessionFactory sessionFactory;

	public void insert(LocationHis his) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Object obj = session.save(his);
		log.info("insert return object = " + obj.getClass() + " ===== " + obj);
		session.getTransaction().commit();
		session.close();
	}

	public void update(LocationHis his) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(his);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(LocationHis his) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(his);
		session.getTransaction().commit();
		session.close();
	}

	public List<LocationHis> queryAll(LocationHis his)
	{
		Session session = sessionFactory.openSession();
		String sql = "select HIS from LocationHis HIS where 1= 1 ";
		if (null != his.getUserId() && !"".equals(his.getUserId())){
			sql += " and HIS.imei = '" + his.getUserId() + "'";// 
		}
		Query query = session.createQuery(sql);
		List<LocationHis> userList = new ArrayList<LocationHis>();
		userList = query.list();
		session.close();
		return userList;
	}

	
	/**
	 * getters and setters
	 * */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
