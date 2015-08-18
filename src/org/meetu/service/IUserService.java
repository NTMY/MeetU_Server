package org.meetu.service;

import java.math.BigDecimal;
import java.util.List;

import org.meetu.model.User;

public interface IUserService {
	
	int insert(User u) throws Exception;

	void update(User u) throws Exception;
	
	List<User> queryList(User u);
	
	User queryById(User u) throws Exception;
	
	int queryNearUsersCount(BigDecimal longitude , BigDecimal latitude);
	
	/**
	 * 通过level查询用户
	 * @param level 用户级别
	 * @oper 操作符 > < >= <= = ....
	 * */
	List<User> selectByLevel(int level , String oper) throws Exception;
	
}
