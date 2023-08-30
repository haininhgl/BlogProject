package com.bezkoder.spring.jwt.mongodb.service;

import com.bezkoder.spring.jwt.mongodb.dto.filter.PostFilter;
import com.bezkoder.spring.jwt.mongodb.exception.ForbiddenException;
import com.bezkoder.spring.jwt.mongodb.exception.ResourceNotFoundException;
import com.bezkoder.spring.jwt.mongodb.entity.Post;
import com.bezkoder.spring.jwt.mongodb.request.PostRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface PostService {
    Page<Post> getAllPost(PostFilter filter, Pageable pageable);

    Post createPost(PostRequest request) throws ResourceNotFoundException;

    Post getById(String id) throws ResourceNotFoundException;

    Post updateById(String id, PostRequest postRequest) throws ForbiddenException, ResourceNotFoundException;

    void deleteById(String id) throws ResourceNotFoundException, ForbiddenException;

}
