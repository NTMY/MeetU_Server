package org.meetu.service;

import java.math.BigDecimal;
import java.util.List;

import org.meetu.model.User;

public interface IUserService {
	
	int insert(User u) throws Exception;

	List<User> queryList(User u);
	
	int queryNearUsersCount(BigDecimal longitude , BigDecimal latitude);
}
