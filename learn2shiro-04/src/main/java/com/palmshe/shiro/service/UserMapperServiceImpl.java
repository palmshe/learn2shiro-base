package com.palmshe.shiro.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.dao.DataAccessException;

import com.palmshe.shiro.dao.UserMapper;
import com.palmshe.shiro.entity.User;

public class UserMapperServiceImpl implements UserMapperService{


	@Resource 
	private UserMapper userMapper;
	
	@Override
	public User getUserByName(String username) throws DataAccessException {
		return this.userMapper.getUserByName(username);
	}

	@Override
	public List<String> getPermissionByUsername(String username) throws DataAccessException {
		return this.userMapper.getPermissionByUsername(username);
	}

	@Override
	public List<String> getRoleByUsername(String username) throws DataAccessException {
		return this.userMapper.getRoleByUsername(username);
	}

}
