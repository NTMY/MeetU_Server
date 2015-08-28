package org.meetu.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.meetu.dao.UserDao;
import org.meetu.model.User;
import org.meetu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;

	@Override
	public int insert(User u) throws Exception {
		int retPK =	userDao.insert(u);
		return retPK;
	}
	
	@Override
	public void update(User u) throws Exception {
		userDao.update(u);
	}
	
	@Override
	public List<User> queryList(User user) {
		List<User> list = userDao.selectListAll(user);
		return list;
	}

	/**
	 * 根据userId查询单个用户
	 * */
	@Override
	public User queryById(User user) throws Exception {
		user = userDao.selectById(user);
		return user;
	}
	
	/**
	 * 查找附近的人个数
	 * */
	@Override
	public int queryNearUsersCount(BigDecimal longitude, BigDecimal latitude) {
		return 0;
	}
	
	@Override
	public List<User> selectByLevel(int level , String oper) throws Exception {
		List<User> list = userDao.selectByLevel(level, oper);
		return list;
	}
	
	
}
