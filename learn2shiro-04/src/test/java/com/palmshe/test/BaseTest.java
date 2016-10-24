package com.palmshe.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.palmshe.shiro.dao.PermissionMapper;
import com.palmshe.shiro.dao.RoleMapper;
import com.palmshe.shiro.dao.RolePermissionMapper;
import com.palmshe.shiro.dao.UserMapper;
import com.palmshe.shiro.dao.UserRoleMapper;
import com.palmshe.shiro.entity.Permission;
import com.palmshe.shiro.entity.Role;
import com.palmshe.shiro.entity.RolePermissionKey;
import com.palmshe.shiro.entity.User;
import com.palmshe.shiro.entity.UserRoleKey;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml", "classpath:spring-mybatis.xml"})
public class BaseTest {
	
	@Resource
	private UserMapper iUserMapper;
	@Resource
	private RoleMapper iRoleMapper;
	@Resource
	private PermissionMapper iPermissionMapper;
	@Resource
	private RolePermissionMapper iRolePermissionMapper;
	@Resource
	private UserRoleMapper iUserRoleMapper;
	
	@Test
	public void initUserRolePermissionData(){
		//初始化用户
		User user= new User("admin", "123456", null, false);
		iUserMapper.addUser(user);
		User user1= new User("songxiong", "123", null, false);
		iUserMapper.addUser(user1);
		User user2= new User("huangjl", "123", null, false);
		iUserMapper.addUser(user2);
		//初始化角色
		Role role= new Role("administrator", "管理员", true);
		iRoleMapper.addRole(role);
		Role role1= new Role("engineer", "工程师", true);
		iRoleMapper.addRole(role1);
		Role role2= new Role("manager", "经理", true);
		iRoleMapper.addRole(role2);
		//初始化权限
		Permission permission= new Permission("appModule:addApp", "增加App权限", true);
		iPermissionMapper.addPermission(permission);
		Permission permission1= new Permission("appModule:uptApp", "修改App权限", true);
		iPermissionMapper.addPermission(permission1);
		Permission permission2= new Permission("appModule:deltApp", "删除App权限", true);
		iPermissionMapper.addPermission(permission2);
		Permission permission3= new Permission("appModule:qryApp", "查询App权限", true);
		iPermissionMapper.addPermission(permission3);
		//用户角色关联
		List<UserRoleKey> collection= new ArrayList<UserRoleKey>();
		UserRoleKey userRole= new UserRoleKey();
		userRole.setUserId(user.getId());
		userRole.setRoleId(role.getId());
		collection.add(userRole);
		this.iUserRoleMapper.associate(collection);
		List<UserRoleKey> collection1= new ArrayList<UserRoleKey>();
		UserRoleKey userRole1= new UserRoleKey();
		userRole1.setUserId(user1.getId());
		userRole1.setRoleId(role1.getId());
		collection1.add(userRole1);
		this.iUserRoleMapper.associate(collection1);
		List<UserRoleKey> collection2= new ArrayList<UserRoleKey>();
		UserRoleKey userRole2= new UserRoleKey();
		userRole2.setUserId(user2.getId());
		userRole2.setRoleId(role2.getId());
		collection2.add(userRole2);
		this.iUserRoleMapper.associate(collection2);
		//角色权限关联
		//administrator
		List<RolePermissionKey> list= new ArrayList<RolePermissionKey>();
		RolePermissionKey rolePermission= new RolePermissionKey();
		rolePermission.setRoleId(role.getId());
		rolePermission.setPermissionId(permission.getId());
		list.add(rolePermission);
		RolePermissionKey rolePermission1= new RolePermissionKey();
		rolePermission1.setRoleId(role.getId());
		rolePermission1.setPermissionId(permission1.getId());
		list.add(rolePermission1);
		RolePermissionKey rolePermission2= new RolePermissionKey();
		rolePermission2.setRoleId(role.getId());
		rolePermission2.setPermissionId(permission2.getId());
		list.add(rolePermission2);
		RolePermissionKey rolePermission3= new RolePermissionKey();
		rolePermission3.setRoleId(role.getId());
		rolePermission3.setPermissionId(permission3.getId());
		list.add(rolePermission3);
		this.iRolePermissionMapper.associate(list);
		//manager
		List<RolePermissionKey> list00= new ArrayList<RolePermissionKey>();
		RolePermissionKey rolePermission01= new RolePermissionKey();
		rolePermission01.setRoleId(role2.getId());
		rolePermission01.setPermissionId(permission3.getId());
		list00.add(rolePermission01);
		this.iRolePermissionMapper.associate(list00);
		//engineer
		List<RolePermissionKey> list000= new ArrayList<RolePermissionKey>();
		RolePermissionKey rolePermission001= new RolePermissionKey();
		rolePermission001.setRoleId(role1.getId());
		rolePermission001.setPermissionId(permission3.getId());
		list000.add(rolePermission001);
		RolePermissionKey rolePermission002= new RolePermissionKey();
		rolePermission002.setRoleId(role1.getId());
		rolePermission002.setPermissionId(permission1.getId());
		list000.add(rolePermission002);
		this.iRolePermissionMapper.associate(list000);
	}

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
