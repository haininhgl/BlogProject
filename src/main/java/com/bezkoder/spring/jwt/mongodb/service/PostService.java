package com.bezkoder.spring.jwt.mongodb.service;

import com.bezkoder.spring.jwt.mongodb.dto.PostDTO;
import com.bezkoder.spring.jwt.mongodb.dto.filter.PostFilter;
import com.bezkoder.spring.jwt.mongodb.exception.ForbiddenException;
import com.bezkoder.spring.jwt.mongodb.exception.ResourceNotFoundException;
import com.bezkoder.spring.jwt.mongodb.entity.Post;
import com.bezkoder.spring.jwt.mongodb.request.PostRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface PostService {
    Page<Post> getAllPost(PostFilter filter, Pageable pageable);

    Post createPost(PostRequest request);

    List<PostDTO> getPostsByTitle(String title);

    Post updateById(String id, PostRequest postRequest) throws ForbiddenException;

    void deleteById(String id) throws ResourceNotFoundException, ForbiddenException;

}
