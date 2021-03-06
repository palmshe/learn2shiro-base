package com.palmshe.shiro.dao;

import java.util.List;

import org.apache.shiro.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.palmshe.shiro.entity.User;

@Repository
public interface UserMapper {

	void addUser(User user) throws DataAccessException;

	User getUserByName(String username) throws DataAccessException;

	List<String> getPermissionByUsername(String username) throws DataAccessException;

	List<String> getRoleByUsername(String username) throws DataAccessException;

}