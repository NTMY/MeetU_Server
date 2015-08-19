package org.meetu.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
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

	public List queryPushInfo(List userIdList) {
		Session session = sessionFactory.openSession();
		String param = userIdList.toString().replace("[","").replace("]","");
		String sql = "select u.id , u.mobile , p.channelId from u_user u join push_info_baidu p on u.id = p.userId where u.id in ("
				+ param + ")";
		List list = null;
		Query query = session.createSQLQuery(sql);
		list = query.list();
		session.close();
		return list;
		
	}

}
