package com.palmshe.shiro.dao;

import java.util.List;

import org.apache.shiro.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.palmshe.shiro.entity.RolePermissionKey;
@Repository
public interface RolePermissionMapper {

	void associate(List<RolePermissionKey> rolePermissionList)throws DataAccessException;
	void unAssociate(List<RolePermissionKey> rolePermissionList)throws DataAccessException;
}