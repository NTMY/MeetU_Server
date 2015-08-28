package org.meetu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.meetu.model.FriendRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 好友关系
 * */
@Repository
public class FriendRelDao {

	private static final Log logger = LogFactory.getLog(FriendRelDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(FriendRel rel) {
		Session session = sessionFactory.getCurrentSession();
		Object pk =  session.save(rel);
		logger.info("return PK = " + pk);
	}

	public void update(FriendRel rel) {
		Session session = sessionFactory.getCurrentSession();
		session.update(rel);
	}

	/**
	 * 
	 * */
	public List<FriendRel> selectListAll(FriendRel rel) {
		Session session = sessionFactory.getCurrentSession();
		StringBuffer hql = new StringBuffer("select R from FriendRel R where 1 = 1 ");
		Query query = session.createQuery(hql.toString());
		List<FriendRel> list = new ArrayList<FriendRel>();
		list = query.list();
		return list;
	}

	/**
	 * 根据userId查询所有好友
	 * */
	public List<FriendRel> selectByUserId(Integer userId) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		// from后面是类名，不是表名
		String hql = "from FriendRel R where R.userId = :userId";// 使用命名参数，推荐使用，易读。
		Query query = session.createQuery(hql);
		List<FriendRel> list = new ArrayList<FriendRel>();
		list = query.list();
		return list;
	}
	
	/**
	 * 查询我的好友,及相关用户信息
	 * @param userId 用户id
	 * @param status 好友关系(正常,黑名单...)
	 * @param mobile
	 * */
	public List<Object[]> selectMyFriend(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		List<Object[]> list = new ArrayList<>();
		StringBuffer hql = new StringBuffer();
		hql.append("select REL.friendId , REL. from u_friends_rel REL");
		Query query = session.createSQLQuery(hql.toString());
		return list;
	}


}
