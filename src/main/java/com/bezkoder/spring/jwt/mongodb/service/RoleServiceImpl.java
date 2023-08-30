package com.bezkoder.spring.jwt.mongodb.service;

import com.bezkoder.spring.jwt.mongodb.entity.RoleType;
import com.bezkoder.spring.jwt.mongodb.exception.ResourceNotFoundException;
import com.bezkoder.spring.jwt.mongodb.entity.Role;
import com.bezkoder.spring.jwt.mongodb.repository.RoleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleById(String id) throws ResourceNotFoundException {
        Role role = roleRepository.findById(id).orElse(null);
        if (role == null){
            throw new ResourceNotFoundException("Ko tim thay");
        }
        return role;
    }

    @Override
    public Set<Role> getRolesByIds(Set<String> ids) {
        return roleRepository.findByIdIn(ids);
    }

    @Nullable
    @Override
    public Role getRoleAdmin() {
        return roleRepository.findByName(RoleType.ROLE_ADMIN).orElse(null);
    }
}
