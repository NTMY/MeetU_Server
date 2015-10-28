package org.meetu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.meetu.model.LocationHis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LocationHisDao {
	private static final Log log = LogFactory.getLog(LocationHisDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void insert(LocationHis his) {
		Session session = sessionFactory.getCurrentSession();
		log.warn("His Sess  " + session.hashCode());
		Object obj = session.save(his);
		log.info("insert return object = " + obj.getClass() + " ===== " + obj);
	}

	public void update(LocationHis his) {
		Session session = sessionFactory.getCurrentSession();
		session.update(his);
	}

	public void delete(LocationHis his) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(his);
	}

	public List<LocationHis> queryAll(LocationHis his)
	{
		Session session = sessionFactory.getCurrentSession();
		String sql = "select HIS from LocationHis HIS where 1= 1 order by id";
		Query query = session.createQuery(sql);
		List<LocationHis> userList = new ArrayList<LocationHis>();
		userList = query.list();
		return userList;
	}

	

}
