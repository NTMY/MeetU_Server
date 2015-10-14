package org.meetu.service.impl;

import java.util.List;

import org.meetu.dao.FriendRelDao;
import org.meetu.model.FriendRel;
import org.meetu.service.IFriendRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户关系Service实现类
 * */
@Service
public class FriendRelServiceImpl implements IFriendRelService {

	@Autowired
	private FriendRelDao relDao;

	@Override
	public void insert(FriendRel rel) throws Exception {
		relDao.insert(rel);
	}

	@Override
	public void update(FriendRel rel) {
		relDao.update(rel);
	}

	@Override
	public List<Object[]> queryMyFriendList(Integer userId, String statusRel)
			throws Exception {
		List<Object[]> list = relDao.selectMyFriend(userId,statusRel);
		
		return list;
	}
	

}
