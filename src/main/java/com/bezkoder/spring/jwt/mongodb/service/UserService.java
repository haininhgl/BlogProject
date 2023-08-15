package com.bezkoder.spring.jwt.mongodb.service;

import com.bezkoder.spring.jwt.mongodb.dto.filter.UserFilter;
import com.bezkoder.spring.jwt.mongodb.exception.ForbiddenException;
import com.bezkoder.spring.jwt.mongodb.exception.ResourceNotFoundException;
import com.bezkoder.spring.jwt.mongodb.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<User> getAllUser(UserFilter userFilter, Pageable pageable) throws ResourceNotFoundException;

    User getUserById(String id) throws ResourceNotFoundException;

    User createUser(User user);

    User getUserByLogin(String login) throws ResourceNotFoundException;

    User updateUser(User user) throws ResourceNotFoundException, ForbiddenException;

    User getCurrentLoginUser() throws ResourceNotFoundException;

    void deleteUser(String id) throws ResourceNotFoundException;
}
