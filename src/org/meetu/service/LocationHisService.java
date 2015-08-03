package org.meetu.service;

import org.meetu.dao.LocationHisDao;
import org.meetu.model.LocationHis;

public class LocationHisService implements ILocationHisService {

	
	private LocationHisDao hisDao;
	
	@Override
	public void insert(LocationHis his) {
		hisDao.insert(his);
	}
	
	@Override
	public void delete(LocationHis his) {
		hisDao.delete(his);		
	}

	
	
	public LocationHisDao getHisDao() {
		return hisDao;
	}

	public void setHisDao(LocationHisDao hisDao) {
		this.hisDao = hisDao;
	}
	
	
}
