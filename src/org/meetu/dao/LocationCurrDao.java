package org.meetu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.meetu.model.LocationCurr;
import org.meetu.util.TimeUtil;

public class LocationCurrDao {
	private static final Log log = LogFactory.getLog(LocationCurrDao.class);

	private SessionFactory sessionFactory;

	public void insert(LocationCurr loc) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// 2 ways : Serializable save()/void persist()
		Object obj = session.save(loc);
		log.info("insert return object = " + obj.getClass() + " ===== " + obj);
		session.getTransaction().commit();
		session.close();
	}

	public void update(LocationCurr loc) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(loc);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(LocationCurr loc) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(loc);
		session.getTransaction().commit();
		session.close();
	}
	
	 public void deleteByUserId(Integer id) {  
	        Session session = sessionFactory.openSession();  
	        session.beginTransaction();  
	        String hql = "delete from LocationCurr where userId = ?";  
	        Query query = session.createQuery(hql);  
	        query.setInteger(0, id);  
	        query.executeUpdate();  
	        session.getTransaction().commit();
	        session.close();  
	    }  

	/**
	 * 查询附近的人(正方形)
	 * */
	public List<LocationCurr> queryNear(LocationCurr curr) {
		Session session = sessionFactory.openSession();
		String sql = "select Loc from LocationCurr Loc where 1= 1 ";
		if (null != curr.getUserId() && !"".equals(curr.getUserId())){
			sql += " and Loc.userId <> '" + curr.getUserId() + "'";//不是自己 
		}
		if (null != curr.getUploadTime() && !"".equals(curr.getUploadTime())){
			sql += " and Loc.uploadTime > '" + TimeUtil.parseDate2Str(TimeUtil.calcTime(new Date(), "-", 2, "hour")) + "'";// 2小时以内
		}
		if (null != curr.getMinLat() && !"".equals(curr.getMinLat())){
			sql += " and Loc.latitude >= '" + curr.getMinLat() + "'";//
		}
		if (null != curr.getMaxLat() && !"".equals(curr.getMaxLat())){
			sql += " and Loc.latitude <= '" + curr.getMaxLat() + "'";//
		}
		if (null != curr.getMinLong() && !"".equals(curr.getMinLong())){
			sql += " and Loc.longitude >= '" + curr.getMinLong() + "'";//
		}
		if (null != curr.getMaxLong() && !"".equals(curr.getMaxLong())){
			sql += " and Loc.longitude <= '" + curr.getMaxLong() + "'";//
		}
		Query query = session.createQuery(sql);
		List<LocationCurr> userList = new ArrayList<LocationCurr>();
		userList = query.list();
		session.close();
		return userList;
	}
	

	public List<LocationCurr> queryAll(LocationCurr curr)
	{
		Session session = sessionFactory.openSession();
		String sql = "select Loc from LocationCurr Loc where 1= 1 ";
		if (null != curr.getUserId() && !"".equals(curr.getUserId())){
			sql += " and Loc.userId = '" + curr.getUserId() + "'";// 
		}
		Query query = session.createQuery(sql);
		List<LocationCurr> userList = new ArrayList<LocationCurr>();
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
