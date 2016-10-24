package com.palmshe.shiro.dao;

import org.apache.shiro.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.palmshe.shiro.entity.Permission;

@Repository
public interface PermissionMapper {
	void addPermission(Permission permission) throws DataAccessException;
}