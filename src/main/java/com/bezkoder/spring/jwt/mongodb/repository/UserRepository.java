package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.entity.User;

public interface UserRepository extends MongoRepository<User, String>,CustomizedUserRepository {
  Optional<User> findByUsername(String username);

  Page<User> findAll(Pageable pageable);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
