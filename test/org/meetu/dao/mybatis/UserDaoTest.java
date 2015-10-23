package org.meetu.dao.mybatis;


import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.meetu.model.LocationCurr;
import org.meetu.model.LocationHis;
import org.meetu.model.User;


public class UserDaoTest {
	
	UserMapper dao = new UserMapper();
	
	
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
	
	
	@Test
	public void testQueryLocCurr() {
		LocationCurr curr = new LocationCurr();
		curr.setUserId(4);
		LocationCurr res = dao.queryLocCurr(curr);
		System.out.println(res);
	}
	
	@Test
	public void testQueryLocHis() {
		LocationHis his = new LocationHis();
		his.setUserId(4);
		List list = dao.queryLocHis(his);
		System.out.println(list);
	}
	
}

