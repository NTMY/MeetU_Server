package org.meetu.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.meetu.model.DeviceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 设备信息dao
 * */
@Repository
public class DeviceInfoDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(DeviceInfo device){
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(device);
	}
	

	
}
