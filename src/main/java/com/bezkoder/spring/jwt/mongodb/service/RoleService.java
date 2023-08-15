package com.bezkoder.spring.jwt.mongodb.service;

import com.bezkoder.spring.jwt.mongodb.exception.BadRequestException;
import com.bezkoder.spring.jwt.mongodb.exception.ResourceNotFoundException;
import com.bezkoder.spring.jwt.mongodb.entity.Role;

import java.util.Set;

public interface RoleService {

    Role getRoleById(String id) throws ResourceNotFoundException, BadRequestException;

    Set<Role> getRolesByIds(Set<String> ids);

    Role getRoleAdmin();
}
