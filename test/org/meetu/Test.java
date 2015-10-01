package org.meetu;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

public class Test {
	public static void main(String[] args) throws Exception {
		String a = "1.0.01";
		String b = "1.2.3";
		String c = "1.2";
		
		String[] str = c.split("\\.");
		System.out.println(Arrays.toString(str));
		
		
		InputStream in = new FileInputStream("");
		
	}
	
	
}
