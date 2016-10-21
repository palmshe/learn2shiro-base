package com.palmshe.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;


/**
 * @author xiong.song
 *
 */
public class LoginLogoutTest {
	
	/**
	 * 通过配置shiro.ini配置文件构建出shiro管理结构
	 * Realm域使用最默认的IniRealm
	 * 进行登录、登出测试
	 */
	@Test
	public void testLoginLogout(){
		//通过配置文件初始化SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//创建SecurityManager实例，并绑定到SecurityUtils工具类上，做全局使用工具
		SecurityManager manager= factory.getInstance();
		SecurityUtils.setSecurityManager(manager);
		//获取Subject身份对象，该对象是源数据集合对象
		Subject subject= SecurityUtils.getSubject();
		//任意指定身份信息，构建出认证token
		UsernamePasswordToken token= new UsernamePasswordToken("songxiong", "123");
		//登录，进行认证，此时通过默认的认证策略IniRealm
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		//断言是否登录认证通过
		Assert.assertEquals(true, subject.isAuthenticated());//断言失败后，后面的程序不执行
		//退出
		subject.logout();
	}
	
	
	/**
	 * 通过配置shiro02.ini配置文件构建出shiro管理结构
	 * Realm域使用最自定义palmsheRealm01
	 * 进行登录、登出测试
	 */
	@Test
	public void testLoginLogout02(){
		//通过配置文件初始化SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro02.ini");
		//创建SecurityManager实例，并绑定到SecurityUtils工具类上，做全局使用工具
		SecurityManager manager= factory.getInstance();
		SecurityUtils.setSecurityManager(manager);
		//获取Subject身份对象，该对象是源数据集合对象
		Subject subject= SecurityUtils.getSubject();
		//任意指定身份信息，构建出认证token
		UsernamePasswordToken token= new UsernamePasswordToken("admin", "123456");
		//登录，进行认证，此时通过自定义认证策略palmsheRealm01
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		//断言是否登录认证通过
		Assert.assertEquals(true, subject.isAuthenticated());//断言失败后，后面的程序不执行
		//退出
		subject.logout();
	}
	
	/**
	 * 通过配置shiro03.ini配置文件构建出shiro管理结构
	 * Realm域使用两个自定义palmsheRealm01，palmsheRealm02
	 * 进行登录、登出测试
	 */
	@Test
	public void testLoginLogout03(){
		//通过配置文件初始化SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro03.ini");
		//创建SecurityManager实例，并绑定到SecurityUtils工具类上，做全局使用工具
		SecurityManager manager= factory.getInstance();
		SecurityUtils.setSecurityManager(manager);
		//获取Subject身份对象，该对象是源数据集合对象
		Subject subject= SecurityUtils.getSubject();
		//任意指定身份信息，构建出认证token
		//定义多个认证策略后，默认的多个策略间的作用关系为Or关系，表示只要一个通过认证，则认证通过，否则抛出AuthenticationException
		UsernamePasswordToken token= new UsernamePasswordToken("songxiong", "123");
		//UsernamePasswordToken token= new UsernamePasswordToken("admin", "123456");
		//登录，进行认证，此时通过自定义认证策略palmsheRealm01
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		//断言是否登录认证通过
		Assert.assertEquals(true, subject.isAuthenticated());//断言失败后，后面的程序不执行
		//退出
		subject.logout();
	}
	
	/**
	 * 通过配置shiro04.ini配置文件构建出shiro管理结构
	 * Realm域使用默认的jdbcRealm，需要指定数据源
	 * 数据库中字段根绝jdbcRealm源码构建，参考learn2shiro-01/src/test/resources/jdbcRealm.sql
	 * 进行登录、登出测试
	 */
	@Test
	public void testLoginLogout04(){
		//通过配置文件初始化SecurityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro04.ini");
		//创建SecurityManager实例，并绑定到SecurityUtils工具类上，做全局使用工具
		SecurityManager manager= factory.getInstance();
		SecurityUtils.setSecurityManager(manager);
		//获取Subject身份对象，该对象是源数据集合对象
		Subject subject= SecurityUtils.getSubject();
		//任意指定身份信息，构建出认证token
		UsernamePasswordToken token= new UsernamePasswordToken("admin", "123456");
		//登录，进行认证，此时通过jdbcRealm进行认证
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		//断言是否登录认证通过
		Assert.assertEquals(true, subject.isAuthenticated());//断言失败后，后面的程序不执行
		//退出
		subject.logout();
	}
	
	/**
	 * 封装登录功能
	 * @param configPath 配置文件路径
	 * @return Subject对象
	 * @throws AuthenticationException 认证异常
	 */
	private Subject login(String userName, String pwd, String configPath) throws AuthenticationException{
		//通过配置文件初始化SecurityManager工厂
				Factory<SecurityManager> factory = new IniSecurityManagerFactory(configPath);
				//创建SecurityManager实例，并绑定到SecurityUtils工具类上，做全局使用工具
				SecurityManager manager= factory.getInstance();
				SecurityUtils.setSecurityManager(manager);
				//获取Subject身份对象，该对象是源数据集合对象
				Subject subject= SecurityUtils.getSubject();
				//任意指定身份信息，构建出认证token
				UsernamePasswordToken token= new UsernamePasswordToken(userName, pwd);
				//登录，进行认证，此时通过jdbcRealm进行认证
				subject.login(token);
				return subject;
	}
	
	/**
	 * 认证策略为AllSuccessfulStrategy下的测试
	 * 保证每个Realm返回的认证信息不一样，才能获得同样数目的不同身份信息
	 */
	@Test
	public void testAllSuccessfulStrategy(){
		Subject subject= null;
		try {
			subject= login("admin", "123456", "classpath:shiro05.ini");
			Assert.assertEquals(2, subject.getPrincipals().asList().size());
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}finally {
			if(subject!= null && subject.isAuthenticated()){
				subject.logout();
			}
		}
	}
}
