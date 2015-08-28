package org.meetu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.meetu.model.PushInfoBaidu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * baidu推送用户数据
 * */
@Repository
public class PushBaiduDao {
	private static final Log log = LogFactory.getLog(PushBaiduDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void insertOrUpdate(PushInfoBaidu push) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(push);
	}

	/**
	 * 通过用户查询用户推送信息
	 * */
	public List<Object[]> queryPushInfo(List userIdList) {
		if(userIdList == null || userIdList.size() == 0) {
			return null;
		}
		Session session = sessionFactory.getCurrentSession();
		//list.toString()之后 带[],要去掉
		String param = userIdList.toString().replace("[","").replace("]","");
		String sql = "select u.id , u.mobile , p.channelId from u_user u join push_info_baidu p on u.id = p.userId where u.id in ("+ param + ")";

		List<Object[]> list = new ArrayList<>();
		Query query = session.createSQLQuery(sql);
		list = query.list();
		return list;
		
	}

}
