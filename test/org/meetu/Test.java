package org.meetu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

import org.meetu.util.RangeCalculator;
import org.meetu.util.TimeUtil;

public class Test {
	public static void main(String[] args) throws UnsupportedEncodingException {

		String str = "é«æ";
		byte[] b = str.getBytes("ISO8859-1");
		str = new String(b , "UTF8");
		System.out.println(str);
	}
}
