package com.palmshe.test;

import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations= {"classpath:applicationContext.xml", "classpath:spring-mybatis.xml"})
public class AuthenAndAuthorTest extends BaseTest{
	
	

	@Test
	public void testAuthenAndAuthor(){
		login("admin", "123456", "classpath:shiro.ini");
		Subject subject= getSubject();
		Assert.assertEquals(true, subject.hasRole("administrator"));
		//Assert.assertEquals(true, "doctor");
		Assert.assertEquals(true, subject.isPermittedAll("appModule:addApp", "appModule:uptApp"));
		//Assert.assertEquals(true, subject.isPermittedAll("appModule:addApp", "appModule:fireApp"));
	}
}
