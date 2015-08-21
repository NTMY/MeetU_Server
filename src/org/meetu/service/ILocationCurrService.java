package org.meetu.service;

import java.util.List;

import org.meetu.dto.BaseDto;
import org.meetu.model.LocationCurr;
import org.meetu.util.ListBean;

public interface ILocationCurrService {

	void insert(LocationCurr curr);

	void delete(LocationCurr curr);

	void deleteByUserId(Integer userId);

	List<LocationCurr> queryAll(LocationCurr curr);

	List<LocationCurr> queryNear(LocationCurr curr);

	
	/**
	 * MEETU service层处理
	 * */
	ListBean meetu(LocationCurr curr);

	/**
	 * upload service层处理
	 * */
	BaseDto upload(LocationCurr curr);
}
