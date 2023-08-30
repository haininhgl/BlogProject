package com.bezkoder.spring.jwt.mongodb.service;

import com.bezkoder.spring.jwt.mongodb.exception.ForbiddenException;
import com.bezkoder.spring.jwt.mongodb.exception.ResourceNotFoundException;
import com.bezkoder.spring.jwt.mongodb.entity.Comment;
import com.bezkoder.spring.jwt.mongodb.request.CommentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
public interface CommentService {

    Page<Comment> getAllComment(Pageable pageable) throws ResourceNotFoundException;

    Comment createComment(CommentRequest request) throws ResourceNotFoundException;

    void updateById(String id, CommentRequest request) throws ForbiddenException, ResourceNotFoundException;

    void deleteById(String id) throws ResourceNotFoundException, ForbiddenException;
}
