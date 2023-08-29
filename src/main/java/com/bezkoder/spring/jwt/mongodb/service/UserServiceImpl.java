package com.bezkoder.spring.jwt.mongodb.service;

import com.bezkoder.spring.jwt.mongodb.SecurityUtils;
import com.bezkoder.spring.jwt.mongodb.dto.filter.UserFilter;
import com.bezkoder.spring.jwt.mongodb.exception.ForbiddenException;
import com.bezkoder.spring.jwt.mongodb.exception.ResourceNotFoundException;
import com.bezkoder.spring.jwt.mongodb.entity.Role;
import com.bezkoder.spring.jwt.mongodb.entity.User;
import com.bezkoder.spring.jwt.mongodb.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> getAllUser(UserFilter userFilter, Pageable pageable) throws ResourceNotFoundException {
        return userRepository.getUserList(userFilter, pageable);
    }

    @Override
    public User getUserById(String id) throws ResourceNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    // Tao user
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getCurrentLoginUser() throws ResourceNotFoundException {
        String login = SecurityUtils.getCurrentUserLogin().orElse(null);
        if (login == null) {
            throw new ResourceNotFoundException("Không tìm thấy người dùng đang đăng nhập.");
        }

        User user = getUserByLogin(login);
        return user;
    }


    public User getUserByLogin(String login) throws ResourceNotFoundException {
        User user = userRepository.findByUsername(login).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("Không tìm thấy người dùng.");
        }

        return user;
    }

    // Cap nhat user
    @Override
    public User updateUser(User user) throws ResourceNotFoundException, ForbiddenException {
        User currentUser = userRepository.findById(user.getId()).orElseThrow(()
                -> new ResourceNotFoundException("User not found"));

        User userLogin = getCurrentLoginUser();

        Set<String> newRoleIds = user.getRoles().stream().map(Role::getId).collect(Collectors.toSet());
        Set<String> currentRoleIds = currentUser.getRoles().stream().map(Role::getId).collect(Collectors.toSet());

        if (!user.equals(userLogin) || !newRoleIds.equals(currentRoleIds)) {
            throw new ForbiddenException("Thao tác không hợp lệ");
        }

        return userRepository.save(currentUser);
    }


    // Xoa user
    @Override
    public void deleteUser(String id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("User not found!");
        }

        userRepository.deleteById(id);
    }
}
