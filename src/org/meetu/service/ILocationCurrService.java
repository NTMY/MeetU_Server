package org.meetu.service;

import java.util.List;

import org.meetu.model.LocationCurr;

public interface ILocationCurrService {

	void insert(LocationCurr curr);

	void delete(LocationCurr curr);

	void deleteByUserId(Integer userId);

	List<LocationCurr> queryAll(LocationCurr curr);

	List<LocationCurr> queryNear(LocationCurr curr);

}
