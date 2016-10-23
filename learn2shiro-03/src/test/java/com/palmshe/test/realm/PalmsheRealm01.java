package com.palmshe.test.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class PalmsheRealm01 extends AuthorizingRealm{
	
	private PasswordService passwordService;
	
	public PasswordService getPasswordService() {
		return passwordService;
	}

	public void setPasswordService(PasswordService passwordService) {
		this.passwordService = passwordService;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authrozitionInfo= new SimpleAuthorizationInfo();
		authrozitionInfo.addRole("administrator");
		authrozitionInfo.addStringPermission("appModule:addApp");
		authrozitionInfo.addStringPermission("appModule:qryApp");
		return authrozitionInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String credentials= this.passwordService.encryptPassword("123456");
		//credentials="123456";
		System.out.println(credentials);
		return new SimpleAuthenticationInfo("admin", credentials, this.getName());
	}

}
