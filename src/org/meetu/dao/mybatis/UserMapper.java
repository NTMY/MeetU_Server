package org.meetu.dao.mybatis;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.meetu.model.LocationCurr;
import org.meetu.model.LocationHis;
import org.meetu.model.User;

public class UserMapper implements IUserDao {
	static SqlSessionFactory sqlSessionFactory = null;
	static {
		try {
			String resource = "mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User queryById(String id) {
		SqlSession session = sqlSessionFactory.openSession();
		User user = null;
		try {
			user = (User) session.selectOne("org.meetu.dao.IUserDao.queryById",
					id);
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public HashMap queryById_Map(String id) {
		SqlSession session = sqlSessionFactory.openSession();
		HashMap map = null;
		try {
			map = session.selectOne("org.meetu.dao.IUserDao.queryById_Map", id);
		} finally {
			session.close();
		}
		return map;
	}

	@Override
	public void insert(User u) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			User user = (User) session.selectOne(
					"org.meetu.dao.IUserDao.insert", u);
			System.out.println("------------------ " + user);

		} finally {
			session.close();
		}
	}

	@Override
	public void update(User u) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User u) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection queryList(User u) {
		return null;
	}

	
	@Override
	public LocationCurr queryLocCurr(LocationCurr curr) {
		SqlSession session = sqlSessionFactory.openSession();
		LocationCurr c = null;
		try {
			c = session.selectOne("org.meetu.dao.IUserDao.queryLocCurr", curr);
		} finally {
			session.close();
		}
		return c;
	}

	@Override
	public List queryLocHis(LocationHis his) {
		SqlSession session = sqlSessionFactory.openSession();
		List list = null;
		try {
			list = session.selectList("org.meetu.dao.IUserDao.queryLocHis", his);
		} finally {
			session.close();
		}
		return list;
	}
	
}
