package org.meetu.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.meetu.model.AppVer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 好友关系
 * */
@Repository
public class AppVerDao {

	@Autowired
	private SessionFactory sessionFactory;
	

	/**
	 * 根据OS和appVer查询
	 * */
	public List<AppVer> selectByOsAndVer(AppVer appVer) {
		Session session = sessionFactory.getCurrentSession();
		// from后面是类名，不是表名
		String hql = "from AppVer R where R.os = :os and R.appVer = :appVer";// 使用命名参数，推荐使用，易读。
		Query query = session.createQuery(hql);
		List<AppVer> list = new ArrayList<>();
		list = query.list();
		return list;
	}
	
	/**
	 * 查询所有top版本信息
	 * */
	public List<AppVer> selectTop() {
		Session session = sessionFactory.getCurrentSession();
		// from后面是类名，不是表名
		String hql = "from AppVer R where R.isTop = true";// 使用命名参数，推荐使用，易读。
		Query query = session.createQuery(hql);
		List<AppVer> list = new ArrayList<>();
		list = query.list();
		return list;
	}
	

}
