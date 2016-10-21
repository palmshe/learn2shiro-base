package com.palmshe.test;

import org.junit.Test;

public class JavaGrammarTest {

	/**
	 * 测试null强制转换为String
	 * 结果：null强制转换任何类型都是null
	 */
	@Test
	public void testNull2String(){
		String userName= (String)null;
		System.out.println(userName);
	}
	
	/**
	 * 测试用Null构造String
	 * 结果 ：NullPointerException，不能构造
	 */
	@Test
	public void testBuildString8Null(){
		String tempName= (String)null;
		String userName= new String(tempName);
		System.out.println(userName);
	}
	
	/**
	 * 测试使用String.Valueof(null)
	 * 结果：NullPointerException，不能获取值
	 */
	@Test
	public void testStringValueOfNull(){
		String userName= String.valueOf(null);
		System.out.println(userName);
	}
}
