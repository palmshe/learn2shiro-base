package com.palmshe.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
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
		//任意指定身份信息，通过指定认证策略，构建出认证实例
		UsernamePasswordToken token= new UsernamePasswordToken("songxiong", "123");
		//登录，进行认证
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		//断言是否登录认证通过
		Assert.assertEquals(true, subject.isAuthenticated());
		//退出
		subject.logout();
	}
	
}
