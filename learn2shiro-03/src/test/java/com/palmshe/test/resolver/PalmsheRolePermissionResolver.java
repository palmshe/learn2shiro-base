package com.palmshe.test.resolver;

import java.util.Arrays;
import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

public class PalmsheRolePermissionResolver implements RolePermissionResolver {

	@Override
	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		if("administrator".equals(roleString)){
			return Arrays.asList(new WildcardPermission("menuModule:*"));
		}
		return null;
	}
}
