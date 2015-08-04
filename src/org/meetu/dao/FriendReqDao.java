package org.meetu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.meetu.model.FriendReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 好友请求DAO
 * */
@Repository
public class FriendReqDao {

	private static final Log logger = LogFactory.getLog(FriendReqDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public int insert(FriendReq req) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		int pk = (Integer) session.save(req);
		logger.info("return PK = " + pk);
		session.getTransaction().commit();
		session.close();
		return pk;
	}

	public void update(FriendReq req) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(req);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(FriendReq req) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(req);
		session.getTransaction().commit();
		session.close();
	}

	public List<FriendReq> selectListAll(FriendReq req) {
		Session session = sessionFactory.openSession();
		StringBuffer hql = new StringBuffer("select R from FriendReq R where 1 = 1 ");
		if (null != req.getReqId() && !"".equals(req.getReqId())) {
			hql.append(" and R.reqId = '" + req.getReqId() + "'");// id
		}
		Query query = session.createQuery(hql.toString());
		List<FriendReq> list = new ArrayList<FriendReq>();
		list = query.list();
		return list;
	}

	public FriendReq selectById(FriendReq req) throws Exception {
		Session session = sessionFactory.openSession();
		// from后面是类名，不是表名
		String hql = "from FriendReq R where R.reqId = :reqId";// 使用命名参数，推荐使用，易读。
		Query query = session.createQuery(hql);
		if (req.getReqId() != null && !req.getReqStatus().equals("")) {
			query.setInteger("reqId", req.getReqId());
		} else {
			throw new RuntimeException("好友请求ID为NULL");
		}
		List<FriendReq> list = new ArrayList<FriendReq>();
		list = query.list();
		if (list == null || list.size() != 1) {
			throw new Exception("好友请求查询异常SELECT_BY_ID");
		} else {
			req = list.get(0);
		}
		return req;
	}


}
