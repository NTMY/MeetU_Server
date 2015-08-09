package org.meetu;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) throws UnsupportedEncodingException {

		String str = "é«æ";
		byte[] b = str.getBytes("ISO8859-1");
		str = new String(b , "UTF8");
		System.out.println(str);
	
		List<String> list = new ArrayList();
	
	}
}
