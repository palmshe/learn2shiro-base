package com.palmshe.test.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义带授权器的Realm
 * @author xiong.song
 */
public class PalmsheRealm01 extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo= new SimpleAuthorizationInfo();
		authorizationInfo.addRole("administrator");
		authorizationInfo.addRole("engineer");
		authorizationInfo.addStringPermission("appModule:addApp");
		authorizationInfo.addStringPermission("appModule:qryApp");
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 返回认证信息
		String userName = (String) token.getPrincipal();// 获取token传入的身份，可以传入null
		String pwd = new String((char[]) token.getCredentials());// 获取token传入的凭证，通过调试发现凭证传入后为[c类型，可以传入null
		// 定义认证策略
		if (!"admin".equals(userName)) {
			// 抛出未知用户异常
			throw new UnknownAccountException("用户名/密码不正确");// 防止恶意轮询用户名
		}
		if (!"123456".equals(pwd)) {
			// 抛出凭证不正确异常
			throw new IncorrectCredentialsException("用户名/密码不正确");// 防止恶意轮询用户名
		}
		// 构造一个认证信息返回
		return new SimpleAuthenticationInfo(userName, pwd, getName());
	}
}
