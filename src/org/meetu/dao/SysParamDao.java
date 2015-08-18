package org.meetu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.meetu.model.SysParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SysParamDao {

	private static final Log logger = LogFactory.getLog(PushBaiduDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * 查询所有
	 * @return 所有数据
	 * */
	public List<SysParam> queryAll() {
		Session session = sessionFactory.openSession();
		String sql = "from SysParam P";
		Query query = session.createQuery(sql);
		List<SysParam> list = new ArrayList<SysParam>();
		list = query.list();
		return list;
	}
	
}
