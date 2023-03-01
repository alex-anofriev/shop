package com.shop.site.service;

import com.shop.site.model.Role;
import com.shop.site.model.RoleName;

public interface RoleService extends CommonMethods<Role> {
    Role findRoleByRoleName(RoleName roleName);
}
