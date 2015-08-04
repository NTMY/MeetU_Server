package org.meetu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.meetu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserLocationDao {
	private static final Log log = LogFactory.getLog(UserLocationDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public int insert(User u) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// 2 ways : Serializable save()/void persist()
		int retPK = (Integer) session.save(u);
		log.info("return PK = " + retPK);
		session.getTransaction().commit();
		session.close();
		return retPK;
	}

	public void update(User u) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(u);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(User u) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(u);
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * return all users in DB TABLE _USER without START & LIMIT params
	 */
	public List<User> selectListAll(User user)
	{
		Session session = sessionFactory.openSession();
		String sql = "select U from User U where 1= 1 ";
		if (null != user.getImei() && !"".equals(user.getImei()))
		{
			sql += " and U.imei != '" + user.getImei() + "'";// imei
		}
		Query query = session.createQuery(sql);
		List<User> userList = new ArrayList<User>();
		userList = query.list();
		return userList;
	}
	
	
	/**
	 * getters and setters
	 * */

}
