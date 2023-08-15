package com.bezkoder.spring.jwt.mongodb.repository;

import com.bezkoder.spring.jwt.mongodb.dto.filter.PostFilter;
import com.bezkoder.spring.jwt.mongodb.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomizedPostRepository {
    Page<Post> getPostList(PostFilter filter, Pageable pageable);
}
