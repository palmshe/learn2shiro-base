package com.palmshe.test;

import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;


public class HasPermissionInRealmTest extends BaseTest{

	/**
	 * 通过自定义Realm和自定义RolePermissionResolver来进行权限测试
	 */
	@Test
	public void testHasPermissionInRealm(){
		login("admin", "123456", "classpath:shiro03.ini");
		Subject subject= getSubject();
		Assert.assertEquals(true, subject.hasRole("administrator"));
		Assert.assertEquals(true, subject.isPermitted("appModule:qryApp"));
		Assert.assertEquals(true, subject.hasRole("engineer"));
		Assert.assertEquals(true, subject.isPermitted("appModule:addApp"));
		//下面权限由RolePermissionResolver配置得到
		Assert.assertEquals(true, subject.isPermitted("permissionModule:addPermission"));
	}
	
	/**
	 * 通过JdbcRealm和自定义RolePermissionResolver来进行权限测试
	 */
	@Test
	public void testHasPermissionInJdbcRealm(){
		login("admin", "123456", "classpath:shiro04.ini");
		Subject subject= getSubject();
		Assert.assertEquals(true, subject.hasRole("administrator"));
		Assert.assertEquals(true, subject.isPermitted("appModule:qryApp"));
		Assert.assertEquals(true, subject.hasRole("engineer"));
		Assert.assertEquals(true, subject.isPermitted("appModule:addApp"));
		//下面权限由RolePermissionResolver配置得到
		Assert.assertEquals(true, subject.isPermitted("permissionModule:addPermission"));
	}
}
