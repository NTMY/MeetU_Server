package org.meetu.dao.mybatis;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.meetu.model.LocationCurr;
import org.meetu.model.LocationHis;
import org.meetu.model.User;


/**
 * UserDao接口
 * */
public interface IUserDao {

	void insert(User u);
	
	void update(User u);
	
	void delete(User u);
	
	User queryById(String id);
	HashMap queryById_Map(String id);
	
	Collection queryList(User u);
	
	/**
	 * 一对一
	 * */
	LocationCurr queryLocCurr(LocationCurr curr);
	
	/**
	 * 一对n
	 * */
	List queryLocHis(LocationHis his);
}
