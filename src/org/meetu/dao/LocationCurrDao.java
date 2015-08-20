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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LocationCurrDao {
	private static final Log log = LogFactory.getLog(LocationCurrDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void insert(LocationCurr loc) {
		Session session = sessionFactory.getCurrentSession();
		// 2 ways : Serializable save()/void persist()
		Object obj = session.save(loc);
		log.info("insert return object = " + obj.getClass() + " ===== " + obj);
	}

	public void update(LocationCurr loc) {
		Session session = sessionFactory.getCurrentSession();
		session.update(loc);
	}

	public void delete(LocationCurr loc) {
		Session session = sessionFactory.getCurrentSession();
		log.warn("CURR SESS " + session.hashCode());
		session.delete(loc);
	}

	public void deleteByUserId(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "delete from LocationCurr where userId = ?";
		Query query = session.createQuery(hql);
		query.setInteger(0, id);
		query.executeUpdate();
	}

	/**
	 * 查询附近的人(正方形)
	 * */
	public List<LocationCurr> queryNear(LocationCurr curr) {
		Session session = sessionFactory.getCurrentSession();
		StringBuffer hql = new StringBuffer("select Loc from LocationCurr Loc where 1= 1 ");
		hql.append(" and Loc.userId <> '").append(curr.getUserId()).append("'");// 不能查询出自己
		hql.append(" and Loc.uploadTime > '").append(TimeUtil.parseDate2Str(TimeUtil.calcTime(new Date(), "-",2, "hour"))).append("'");// 2小时以内
		//筛选经纬度,画个正方形
		hql.append(" and Loc.latitude >= '").append(curr.getMinLat()).append("'");//
		hql.append(" and Loc.latitude <= '").append(curr.getMaxLat()).append("'");//
		hql.append(" and Loc.longitude >= '").append(curr.getMinLong()).append("'");//
		hql.append(" and Loc.longitude <= '").append(curr.getMaxLong()).append("'");//
		Query query = session.createQuery(hql.toString());
		List<LocationCurr> locList = new ArrayList<LocationCurr>();
		locList = query.list();
		return locList;
	}

	public List<LocationCurr> queryAll(LocationCurr curr) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select Loc from LocationCurr Loc where 1= 1 ";
		if (null != curr.getUserId() && !"".equals(curr.getUserId())) {
			sql += " and Loc.userId = '" + curr.getUserId() + "'";//
		}
		Query query = session.createQuery(sql);
		List<LocationCurr> userList = new ArrayList<LocationCurr>();
		userList = query.list();
		return userList;
	}

	/**
	 * getters and setters
	 * */

}
