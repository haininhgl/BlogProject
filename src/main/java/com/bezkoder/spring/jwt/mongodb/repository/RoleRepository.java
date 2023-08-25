package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.entity.RoleType;
import com.bezkoder.spring.jwt.mongodb.entity.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(RoleType name);
  Set<Role> findByNameIn(Set<RoleType> names);
  Set<Role> findByIdIn(Set<String> ids);
}
