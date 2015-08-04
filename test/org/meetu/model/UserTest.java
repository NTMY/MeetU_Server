package org.meetu.model;

public class UserTest {

	
	public static void main(String[] args) {
		User user = new User();
		user.setId(1);
		user.setName("高文");
		
		
		User userDB = new User();
		userDB.merge(user);
		System.out.println(userDB);
	}
}
