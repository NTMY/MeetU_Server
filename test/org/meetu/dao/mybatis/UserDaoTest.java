package org.meetu.dao.mybatis;


import java.util.Map;

import org.junit.Test;
import org.meetu.model.User;


public class UserDaoTest {
	
	UserDao dao = new UserDao();
	
	
	@Test
	public void testQueryById() {
		User user = dao.queryById("1");
	}
	
	@Test
	public void testQueryById_Map() {
		Map map = dao.queryById_Map("1");
		System.out.println(map.toString());
	}
	
	@Test
	public void testInsert() {
		User u = new User();
		u.setName("mybatis");
		dao.insert(u);
	}
	
	
}

