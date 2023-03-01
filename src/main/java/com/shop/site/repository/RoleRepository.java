package com.shop.site.repository;

import com.shop.site.model.Role;
import com.shop.site.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByRoleName(RoleName roleName);
}
