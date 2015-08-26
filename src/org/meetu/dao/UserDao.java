package org.meetu.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.meetu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	private static final Log log = LogFactory.getLog(UserDao.class);

	@Autowired
	private SessionFactory sessionFactory;


	public int insert(User u) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		int pk = (Integer)session.save(u);
		log.info("return PK = " + pk);
		return pk;
	}

	public void update(User u) {
		Session session = sessionFactory.getCurrentSession();
		session.update(u);
	}

	public void delete(User u) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(u);
	}

	public List<User> selectListAll(User user)
	{
		Session session = sessionFactory.getCurrentSession();
		StringBuffer hql = new StringBuffer("select U from User U where 1= 1 ");
		//通过id查询
		if (null != user.getId() && !"".equals(user.getId()))
		{
			hql.append(" and U.id = '").append(user.getId()).append("'");
		}
		//通过手机号查询
		if (null != user.getMobile() && !"".equals(user.getMobile()))
		{
			hql.append(" and U.mobile = '").append(user.getMobile()).append("'");
		}
		//通过姓名查询
		if(null != user.getName() && !"".equals(user.getName())) 
		{
			hql.append(" and U.name = '").append(user.getName()).append("'");
		}
		//通过昵称查询
		if(null != user.getNickname() && !"".equals(user.getNickname())) 
		{
			hql.append(" and U.nickname = ").append(user.getNickname()).append("'");
		}
		//通过QQ查询
		if(null != user.getQq() && !"".equals(user.getQq())) 
		{
			hql.append(" and U.qq = '").append(user.getQq()).append("'");
		}
		//通过微信号查询
		if(null != user.getWechat() && !"".equals(user.getWechat()))
		{
			hql.append(" and U.wechat = '").append(user.getWechat()).append("'");
		}
		
		Query query = session.createQuery(hql.toString());
		List<User> userList = new ArrayList<User>();
		userList = query.list();
		return userList;
	}
	
	public User selectById(User user) throws Exception
	{
		Session session = sessionFactory.getCurrentSession();
		String sql = "select U from User U where 1= 1 ";
		if (null != user.getId() && !"".equals(user.getId()))
		{
			sql += " and U.id = '" + user.getId() + "'";// id
		}
		Query query = session.createQuery(sql);
		List<User> userList = new ArrayList<User>();
		userList = query.list();
		if(userList == null || userList.size() > 1) {
			throw new Exception("用户查询异常SELECT_BY_ID");
		} else if(userList.size() == 0) {
			throw new Exception("用户不存在");
		} else {
			user = userList.get(0);
		}
//		session.close();
		return user;
	}
	
	/**
	 * 根据用户LEVEL查询用户
	 * @level 用户级别
	 * @oper 操作符 < > >= <= =
	 * */
	public List<User> selectByLevel(int level , String oper) throws Exception
	{
		Session session = sessionFactory.getCurrentSession();
		String sql = "select U from User U where U.status " + oper  + level;
		Query query = session.createQuery(sql);
		List<User> userList = new ArrayList<User>();
		userList = query.list();
		if(userList == null) { 
			throw new Exception("SELECT BY LEVEL");
		}
//		session.close();
		return userList;
	}
	

	
}
