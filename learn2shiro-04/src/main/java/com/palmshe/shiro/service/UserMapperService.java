package com.palmshe.shiro.service;

import java.util.List;

import org.apache.shiro.dao.DataAccessException;

import com.palmshe.shiro.entity.User;

public interface UserMapperService {
	User getUserByName(String username) throws DataAccessException;

	List<String> getPermissionByUsername(String username) throws DataAccessException;

	List<String> getRoleByUsername(String username) throws DataAccessException;
}
