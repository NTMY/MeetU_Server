package org.meetu.dao.mybatis;

import java.util.Collection;
import java.util.HashMap;

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
}
