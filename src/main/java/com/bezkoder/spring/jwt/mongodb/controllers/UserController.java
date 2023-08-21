package com.bezkoder.spring.jwt.mongodb.controllers;

import com.bezkoder.spring.jwt.mongodb.dto.UserDTO;
import com.bezkoder.spring.jwt.mongodb.dto.filter.UserFilter;
import com.bezkoder.spring.jwt.mongodb.dto.mapper.UserMapper;
import com.bezkoder.spring.jwt.mongodb.exception.BadRequestException;
import com.bezkoder.spring.jwt.mongodb.exception.ForbiddenException;
import com.bezkoder.spring.jwt.mongodb.exception.ResourceNotFoundException;
import com.bezkoder.spring.jwt.mongodb.entity.Role;
import com.bezkoder.spring.jwt.mongodb.entity.User;
import com.bezkoder.spring.jwt.mongodb.request.BasicUserRequest;
import com.bezkoder.spring.jwt.mongodb.request.PaginationRequest;
import com.bezkoder.spring.jwt.mongodb.request.UserRequest;
import com.bezkoder.spring.jwt.mongodb.response.APIResponse;
import com.bezkoder.spring.jwt.mongodb.service.RoleService;
import com.bezkoder.spring.jwt.mongodb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {

    @Value("${maxPageSize}")
    private Integer maxPageSize;

    private final UserService userService;

    private final UserMapper userMapper;

    private final RoleService roleService;

    public UserController(UserService userService, UserMapper userMapper, RoleService roleService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public APIResponse<List<UserDTO>> getUserList(@Valid UserFilter userFilter, PaginationRequest paginationRequest)
            throws ResourceNotFoundException {

        Pageable pageable = paginationRequest.toPageable(maxPageSize);

        Page<User> userPage = userService.getAllUser(userFilter, pageable);
        Page<UserDTO> result = userPage.map(this.userMapper::toDto);
        return APIResponse.newSuccessPageResponse(result);
    }

    //tao user
    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public APIResponse<User> createUser(@RequestBody @Valid UserRequest request) throws BadRequestException {
        User user = validateCreateUser(request);
        userService.createUser(user);
        return APIResponse.newSuccessResponse();
    }

    @GetMapping("/users/info")
    public APIResponse<UserDTO> info() throws ResourceNotFoundException {
        User user = userService.getCurrentLoginUser();

        return APIResponse.newSuccessResponse(this.userMapper.toDto(user));
    }

    //cap nhat user
    @PutMapping("/users/{id}")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public APIResponse<UserDTO> updateUser(@PathVariable String id, @Valid @RequestBody BasicUserRequest request)
            throws ResourceNotFoundException, ForbiddenException {
        User user = userService.getUserById(id);
        user = userService.updateUser(user);

        return APIResponse.newSuccessResponse(this.userMapper.toDto(user));
    }

    @GetMapping("users/{id}")
    public APIResponse<UserDTO> getUserById(@PathVariable String id){
        User user = userService.getUserById(id);
        return APIResponse.newSuccessResponse(this.userMapper.toDto(user));
    }

    //xoa user
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public APIResponse<String> deleteAccountById(@PathVariable String id) throws ResourceNotFoundException {
        userService.deleteUser(id);
        return APIResponse.newSuccessResponse();
    }

    private User validateCreateUser(UserRequest request)
            throws ResourceNotFoundException, BadRequestException {
        User user = new User();

        Set<Role> roleList = roleService.getRolesByIds(request.getRoleIds());
        if (CollectionUtils.isEmpty(roleList)) {
            throw new BadRequestException("Nhóm quyền không được bỏ trống.");
        }

        user.setRoles(roleList);

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        return user;
    }
}

