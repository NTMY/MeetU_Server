package org.meetu.service.impl;

import java.util.List;

import org.meetu.dao.AppVerDao;
import org.meetu.model.AppVer;
import org.meetu.service.IAppVerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppVerServiceImpl implements IAppVerService{

	@Autowired
	private AppVerDao dao;
	
	@Override
	public List<AppVer> queryByOSAndVer(AppVer pk) {
		List<AppVer> list = dao.selectByOsAndVer(pk);
		return list;
	}

	@Override
	public List<AppVer> queryTop() {
		List<AppVer> list = dao.selectTop();
		return list;
	}

	
}
