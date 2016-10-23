package com.palmshe.test;


import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;


/**
 * 测试密钥服务和匹配
 * @author 宋雄
 */
public class PasswordServiceAndMatcherTest extends BaseTest{

	/**
	 * 用自定义Realm测试密钥匹配
	 */
	@Test
	public void testPasswordServiceAndMatcher(){
		login("admin", "123456", "classpath:shiro01.ini");
		Subject subject= getSubject();
		Assert.assertEquals(true, subject.isAuthenticated());
	}
	
	/**
	 * 用JdbcRealm测试密钥匹配
	 */
	@Test
	public void testPasswordServiceAndMatcherInJdbcRealm(){
		login("admin", "123456", "classpath:shiro02.ini");
		Subject subject= getSubject();
		Assert.assertEquals(true, subject.isAuthenticated());
	}
}
