package org.meetu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.meetu.model.FriendRel;
import org.meetu.model.User;
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
		Object pk = session.save(rel);
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
		StringBuffer hql = new StringBuffer(
				"select R from FriendRel R where 1 = 1 ");
		Query query = session.createQuery(hql.toString());
		List<FriendRel> list = new ArrayList<FriendRel>();
		list = query.list();
		return list;
	}

	/**
	 * 查询我的好友,及相关用户信息
	 * 
	 * @param userId
	 *            用户id
	 * @param status
	 *            好友关系(正常,黑名单...) 不传就是全部状态的好友
	 * */
	public List<Object[]> selectMyFriend(Integer userId, String statusRel) {
		Session session = sessionFactory.getCurrentSession();
		List<Object[]> list = new ArrayList<>();
		if (userId == null) {
			return list;
		}
		StringBuffer sql = new StringBuffer(
				"select "
				+ " R.*, U.* "
				+ " from U_FRIENDS_REL R left join U_USER U on R.userId = U.id where 1 = 1 ");
//				"select "
//				+ " R.userId , R.friendId , R.statusRel , R.happenTime , U.name , U.nickname, U.mood ,U.status,U.imgUrl,U.imgUrlHD , U.gender "
//				+ " from U_FRIENDS_REL R left join U_USER U on R.userId = U.id where 1 = 1 ");
		sql.append(" and R.userId = ").append(userId);
		if (statusRel != null) {
			sql.append(" and R.statusRel = ").append(statusRel);
		}
		list = session.createSQLQuery(sql.toString()).addEntity("user",User.class).addEntity("rel",FriendRel.class).list();
		return list;
	}

}
