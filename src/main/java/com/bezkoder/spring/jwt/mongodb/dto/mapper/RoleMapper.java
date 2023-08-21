package com.bezkoder.spring.jwt.mongodb.dto.mapper;

import com.bezkoder.spring.jwt.mongodb.dto.RoleDTO;
import com.bezkoder.spring.jwt.mongodb.entity.Role;
import com.bezkoder.spring.jwt.mongodb.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {
}
