package com.palmshe.test.resolver;

import java.util.Arrays;
import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

public class PalmsheRolePermissonResolver implements RolePermissionResolver {

	@Override
	public Collection<Permission> resolvePermissionsInRole(String role) {
		if("administrator".equals(role)){
			return Arrays.asList(new WildcardPermission("permissionModule:*"));
		}
		return null;
	}
}
