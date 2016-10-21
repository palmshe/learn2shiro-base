package com.palmshe.test;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

public class PermissionTest extends BaseTest {
	
	/**
	 * 通过isPermitted("permission")判断是否拥有权限
	 * 返回值类型boolean
	 */
	@Test
	public void testIsPermission(){
		login("admin", "123456", "classpath:shiro02.ini");
		Subject subject= getSubject();
		Assert.assertEquals(true, subject.isPermitted("appModule:addApp"));
		Assert.assertEquals(true, subject.isPermittedAll("appModule:addApp","fileModule:addFile"));
		boolean[] isPermitteds= subject.isPermitted("appModule:addApp","fileModule:addFile", "fileModule:uptFile");
		Assert.assertEquals(true, isPermitteds[0]);
		Assert.assertEquals(true, isPermitteds[1]);
		Assert.assertEquals(true, isPermitteds[2]);
	}
	
	/**
	 * 通过checkPermission("permission")判断是否拥有权限
	 * 如果没有权限将抛出异常
	 */
	@Test(expected=AuthorizationException.class)
	public void testCheckPermission(){
		login("admin", "123456", "classpath:shiro02.ini");
		Subject subject= getSubject();
		//subject.checkPermission("fileModule:addFile");
		subject.checkPermissions("appModule:addApp","fileModule:addFile");
	}
}
