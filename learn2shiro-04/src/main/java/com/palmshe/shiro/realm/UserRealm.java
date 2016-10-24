package com.palmshe.shiro.realm;

import java.util.HashSet;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.palmshe.shiro.entity.User;
import com.palmshe.shiro.service.UserMapperService;

public class UserRealm extends AuthorizingRealm{
	
	private UserMapperService userMapperService;
	
	public UserRealm(){
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:*.xml");
		userMapperService= (UserMapperService)context.getBean("userServiceImp");
	}
	
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username= (String)principals.getPrimaryPrincipal();
		//通过user下的接口获取数据库中的角色和权限
		List<String> permissions= userMapperService.getPermissionByUsername(username);
		List<String> roles= userMapperService.getRoleByUsername(username);
		SimpleAuthorizationInfo authorizationInfo=  new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(new HashSet<String>(roles));
		authorizationInfo.setStringPermissions(new HashSet<String>(permissions));
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username= (String)token.getPrincipal();
		//通过user下的接口获取用户数据
		User user= this.userMapperService.getUserByName(username);
		if (user== null) {
			throw new UnknownAccountException("账户未知");
		}
		if (user.getLocked()== Boolean.TRUE){
			throw new LockedAccountException("账户锁定");
		}
		return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
	}

}
