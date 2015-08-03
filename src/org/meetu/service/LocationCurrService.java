package org.meetu.service;

import java.util.List;

import org.meetu.dao.LocationCurrDao;
import org.meetu.model.LocationCurr;

public class LocationCurrService implements ILocationCurrService {

	
	private LocationCurrDao currDao;
	
	@Override
	public void insert(LocationCurr curr) {
		currDao.insert(curr);
	}
	
	@Override
	public void delete(LocationCurr curr) {
		currDao.delete(curr);		
	}
	
	@Override
	public void deleteByUserId(Integer userId) {
		currDao.deleteByUserId(userId);		
	}

	@Override
	public List<LocationCurr> queryAll(LocationCurr curr) {
		List<LocationCurr> list = currDao.queryAll(curr);
		return list;
	}

	@Override
	public List<LocationCurr> queryNear(LocationCurr curr) {
		List<LocationCurr> list = currDao.queryNear(curr);
		return list;
	}

	public LocationCurrDao getCurrDao() {
		return currDao;
	}

	public void setCurrDao(LocationCurrDao currDao) {
		this.currDao = currDao;
	}
	



	
	
	
	
}
