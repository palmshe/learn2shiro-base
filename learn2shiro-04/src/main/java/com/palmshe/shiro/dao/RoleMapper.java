package com.palmshe.shiro.dao;

import org.apache.shiro.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.palmshe.shiro.entity.Role;
@Repository
public interface RoleMapper {
  void addRole(Role role)throws DataAccessException;
}