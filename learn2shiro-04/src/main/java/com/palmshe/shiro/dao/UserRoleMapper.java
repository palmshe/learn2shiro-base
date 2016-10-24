package com.palmshe.shiro.dao;

import java.util.List;

import org.apache.shiro.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.palmshe.shiro.entity.UserRoleKey;
@Repository
public interface UserRoleMapper {
	void associate(List<UserRoleKey> userRoleList) throws DataAccessException;
	void unAssociate(List<UserRoleKey> userRoleList)throws DataAccessException;
}