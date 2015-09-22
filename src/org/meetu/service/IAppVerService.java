package org.meetu.service;

import java.util.List;

import org.meetu.model.AppVer;

/**
 * AppVerService接口
 * */
public interface IAppVerService {
	/**
	 * 根据PK查询
	 * */
	List<AppVer> queryByOSAndVer(AppVer pk);
	
	List<AppVer> queryTop();
}
