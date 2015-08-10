package org.meetu;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.meetu.model.User;


public class Test {
	public static void main(String[] args) throws UnsupportedEncodingException {
		User user = new User();
		user.setId(1);
		user.setName("1");
		
		User u = new User();
		u.copyFrom(user);
		System.out.println(u.getId());
	}
}
