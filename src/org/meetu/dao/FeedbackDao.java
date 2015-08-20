package org.meetu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.meetu.model.Feedback;
import org.meetu.model.FriendRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 用户反馈信息DAO
 * */
@Repository
public class FeedbackDao {

	private static final Log logger = LogFactory.getLog(FeedbackDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(Feedback feed) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.save(feed);
	}

		
	/*
	public List<FriendRel> selectListAll(FriendRel rel) {
		Session session = sessionFactory.openSession();
		StringBuffer hql = new StringBuffer("select R from FriendReq R where 1 = 1 ");
//		if (null != rel.getReqId() && !"".equals(rel.getReqId())) {
//			hql.append(" and R.reqId = '" + rel.getReqId() + "'");// id
//		}
		Query query = session.createQuery(hql.toString());
		List<FriendRel> list = new ArrayList<FriendRel>();
		list = query.list();
		return list;
	}

	public FriendRel selectById(FriendRel rel) throws Exception {
		Session session = sessionFactory.openSession();
		// from后面是类名，不是表名
		String hql = "from FriendReq R where R.reqId = :reqId";// 使用命名参数，推荐使用，易读。
		Query query = session.createQuery(hql);
//		if (rel.getReqId() != null && !rel.getReqStatus().equals("")) {
//			query.setInteger("reqId", rel.getReqId());
//		} else {
//			throw new RuntimeException("好友请求ID为NULL");
//		}
		List<FriendRel> list = new ArrayList<FriendRel>();
		list = query.list();
		if (list == null || list.size() != 1) {
			throw new Exception("好友请求查询异常SELECT_BY_ID");
		} else {
			rel = list.get(0);
		}
		return rel;
	}
	*/

}
