package org.meetu.service;

import java.util.List;

import org.meetu.model.AppVer;
import org.meetu.model.key.AppVerPK;

/**
 * AppVerService接口
 * */
public interface IAppVerService {
	/**
	 * 根据PK查询
	 * */
	List<AppVer> queryByPK(AppVerPK pk);
	
	List<AppVer> queryTop();
}
