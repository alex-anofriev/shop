package com.shop.site.service.impl;

import com.shop.site.exception.DataProcessingException;
import com.shop.site.model.Role;
import com.shop.site.model.RoleName;
import com.shop.site.repository.RoleRepository;
import com.shop.site.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Role entity) {
        roleRepository.save(entity);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new DataProcessingException("No role found"));
    }

    @Override
    public void update(Role entity) {
        roleRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findRoleByRoleName(RoleName roleName) {
        return roleRepository.findRoleByRoleName(roleName);
    }
}
