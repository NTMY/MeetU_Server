package org.meetu.service.impl;

import java.util.List;

import org.meetu.dao.SysParamDao;
import org.meetu.model.SysParam;
import org.meetu.service.ISysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统参数Service Impl
 * */
@Service
public class SysParamServiceImpl implements ISysParamService {
	
	@Autowired
	private SysParamDao dao;
	
	@Override
	public List<SysParam> queryAll() {
		return dao.queryAll();
	}

	
}
