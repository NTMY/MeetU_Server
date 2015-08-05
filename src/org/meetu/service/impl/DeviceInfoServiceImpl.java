package org.meetu.service.impl;

import org.meetu.dao.DeviceInfoDao;
import org.meetu.model.DeviceInfo;
import org.meetu.service.IDeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceInfoServiceImpl implements IDeviceInfoService {

	@Autowired
	private DeviceInfoDao dao;
	
	
	@Override
	public void saveOrUpdate(DeviceInfo dev) {
		dao.insert(dev);
	}


}
