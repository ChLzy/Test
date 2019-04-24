package com.chinasofti.util;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.chinasofti.control.Control;


public class Test {
	@org.junit.Test
	public void test() throws ParseException{
		SimpleDateFormat sf=new SimpleDateFormat("MM");
		String s="2019-03-02";
		System.out.println(sf.parse(s));
	}
}
