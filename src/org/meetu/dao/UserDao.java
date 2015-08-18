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
public class UserDao {
	private static final Log log = LogFactory.getLog(UserDao.class);

	@Autowired
	private SessionFactory sessionFactory;


	public int insert(User u) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// 2 ways : Serializable save()/void persist()
		int pk = (Integer)session.save(u);
		log.info("return PK = " + pk);
		session.getTransaction().commit();
		session.close();
		return pk;
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

	public List<User> selectListAll(User user)
	{
		Session session = sessionFactory.openSession();
		String sql = "select U from User U where 1= 1 ";
		if (null != user.getId() && !"".equals(user.getId()))
		{
			sql += " and U.id = '" + user.getId() + "'";// id
		}
		if (null != user.getMobile() && !"".equals(user.getMobile()))
		{
			sql += " and U.mobile = '" + user.getMobile() + "'";// id
		}
		Query query = session.createQuery(sql);
		List<User> userList = new ArrayList<User>();
		userList = query.list();
		return userList;
	}
	
	public User selectById(User user) throws Exception
	{
		Session session = sessionFactory.openSession();
		String sql = "select U from User U where 1= 1 ";
		if (null != user.getId() && !"".equals(user.getId()))
		{
			sql += " and U.id = '" + user.getId() + "'";// id
		}
		Query query = session.createQuery(sql);
		List<User> userList = new ArrayList<User>();
		userList = query.list();
		if(userList == null || userList.size() > 1) {
			throw new Exception("用户查询异常SELECT_BY_ID");
		} else if(userList.size() == 0) {
			throw new Exception("用户不存在");
		} else {
			user = userList.get(0);
		}
		return user;
	}
	
	/**
	 * 查询所有管理员
	 * */
	public List<User> selectByLevel(int level) throws Exception
	{
		Session session = sessionFactory.openSession();
		String sql = "select U from User U where status >= 90 ";
		Query query = session.createQuery(sql);
		List<User> userList = new ArrayList<User>();
		userList = query.list();
		if(userList == null) { 
			throw new Exception("SELECT By LEVEL");
		}
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
