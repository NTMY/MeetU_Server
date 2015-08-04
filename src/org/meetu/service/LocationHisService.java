package org.meetu.service;

import org.meetu.dao.LocationHisDao;
import org.meetu.model.LocationHis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationHisService implements ILocationHisService {

	@Autowired
	private LocationHisDao hisDao;
	
	@Override
	public void insert(LocationHis his) {
		hisDao.insert(his);
	}
	
	@Override
	public void delete(LocationHis his) {
		hisDao.delete(his);		
	}

	
}
