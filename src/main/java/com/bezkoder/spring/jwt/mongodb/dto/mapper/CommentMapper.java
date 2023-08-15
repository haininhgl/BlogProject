package com.bezkoder.spring.jwt.mongodb.dto.mapper;

import com.bezkoder.spring.jwt.mongodb.dto.CommentDTO;
import com.bezkoder.spring.jwt.mongodb.mapper.EntityMapper;
import com.bezkoder.spring.jwt.mongodb.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { UserMapper.class, PostMapper.class })
public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {
}
