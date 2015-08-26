package org.meetu;

import org.meetu.model.User;

public class Test {
	public static void main(String[] args) throws Exception {
		User user = new User();
		user.setId(1);
		user.setName("1");

		User u = new User();
		u.copyFrom(user);
		System.out.println(u.getId());

		System.out.println("/xE6/x92/x92/xE6/x92/x92");
		String s = null;
		try {
			switch (2) {
			case 1:
				System.out.println(1);
				break;
			case 2:
				s.split(" ");
				break;
			}
		} catch (Exception e) {
			System.out.println("catch");
			e.printStackTrace();
		} finally{
			System.out.println("finally");
		}
	}
}
