package com.palmshe.test;

import java.util.Arrays;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

/**
 * 基于角色访问控制
 * @author xiong.song
 */
public class RoleTest extends BaseTest {

	/**
	 * 通过subject.hasRole("role")检查是否拥有权限
	 * 其返回值类型是boolean
	 * 注意一个断言失败，程序将停止
	 */
	@Test
	public void testHasRole(){
		try {
			login("admin", "123456", "classpath:shiro01.ini");
			Subject subject= getSubject();
			Assert.assertEquals(true, subject.hasRole("administrator"));
			Assert.assertEquals(true, subject.hasAllRoles(Arrays.asList("administrator", "engineer")));
			boolean [] isTrues= subject.hasRoles(Arrays.asList("administrator", "engineer", "doctor"));
			Assert.assertEquals(true, isTrues[0]);
			Assert.assertEquals(true, isTrues[1]);
			Assert.assertEquals(true, isTrues[2]);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过subject.checkRole("role")检查是否拥有权限
	 * 如果不存在权限将抛出异常
	 * 这里不是通过断言，而是铜鼓期望来判断是否会产生预期异常
	 * 注意一个断言失败，程序将停止
	 */
	@Test(expected=AuthorizationException.class)
	public void testCheckRole(){
		login("admin", "123456", "classpath:shiro01.ini");
		Subject subject= getSubject();
		//subject.checkRole("doctor");
		//subject.checkRoles("administrator", "engineer", "doctor");
		subject.checkRoles(Arrays.asList("administrator", "engineer", "doctor"));
	}
}
