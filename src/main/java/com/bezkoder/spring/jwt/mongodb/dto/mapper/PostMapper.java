package com.bezkoder.spring.jwt.mongodb.dto.mapper;

import com.bezkoder.spring.jwt.mongodb.dto.PostDTO;
import com.bezkoder.spring.jwt.mongodb.mapper.EntityMapper;
import com.bezkoder.spring.jwt.mongodb.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface PostMapper extends EntityMapper<PostDTO, Post> {
}
