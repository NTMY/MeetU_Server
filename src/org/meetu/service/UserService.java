package org.meetu.service;

import java.math.BigDecimal;
import java.util.List;

import org.meetu.dao.UserDao;
import org.meetu.model.User;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {

	private UserDao userDao;

	@Override
	public int insert(User u) throws Exception {
		int retPK =	userDao.insert(u);
		return retPK;
	}
	
	/**
	 * 查找附近的人具体信息
	 * */
	@Override
	public List<User> queryList(User user) {
		List<User> list = userDao.selectListAll(user);
		return list;
	}

	/**
	 * 查找附近的人个数
	 * */
	@Override
	public int queryNearUsersCount(BigDecimal longitude, BigDecimal latitude) {
		return 0;
	}
	

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
	
}
