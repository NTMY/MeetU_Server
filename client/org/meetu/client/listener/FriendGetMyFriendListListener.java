package org.meetu.client.listener;

import org.meetu.model.User;
import org.meetu.util.ListBean;

/**
 * 获取好友列表listener接口
 * */
public interface FriendGetMyFriendListListener {
	
	/**
	 * 返回所有用户,User对象
	 * */
	@Deprecated
	void getMyFriendList(ListBean<User> beans);
	
	
	/**
	 * 返回真正的好友列表
	 * beans.getList()得到的list,每个元素都是一个Object[],obj[0]是User,obj[1]是FriendRel
	 * */
	void getMyFriendListReal(ListBean<Object[]> beans);
	
}
