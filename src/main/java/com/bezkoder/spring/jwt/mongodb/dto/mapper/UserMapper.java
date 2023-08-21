package com.bezkoder.spring.jwt.mongodb.dto.mapper;

import com.bezkoder.spring.jwt.mongodb.dto.UserDTO;
import com.bezkoder.spring.jwt.mongodb.mapper.EntityMapper;
import com.bezkoder.spring.jwt.mongodb.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { RoleMapper.class })
public interface UserMapper extends EntityMapper<UserDTO, User> {
}

