package com.palmshe.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;

public class BaseTest {
	/**
	 * 封装登录功能
	 * @param configPath 配置文件路径
	 * @return Subject对象
	 * @throws AuthenticationException 认证异常
	 */
	public void login(String userName, String pwd, String configPath) throws AuthenticationException{
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
	}
	
	/**
	 * 获取登录身份对象
	 * @return
	 */
	public Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	@After
	public void tearDown() throws Exception {
		//解除当前线程和身份对象的绑定
		ThreadContext.unbindSubject();
	}
}
