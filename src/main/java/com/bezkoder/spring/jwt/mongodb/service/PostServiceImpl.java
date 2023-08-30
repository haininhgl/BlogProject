package com.bezkoder.spring.jwt.mongodb.service;


import com.bezkoder.spring.jwt.mongodb.dto.filter.PostFilter;
import com.bezkoder.spring.jwt.mongodb.exception.ForbiddenException;
import com.bezkoder.spring.jwt.mongodb.exception.ResourceNotFoundException;
import com.bezkoder.spring.jwt.mongodb.entity.Post;
import com.bezkoder.spring.jwt.mongodb.entity.Role;
import com.bezkoder.spring.jwt.mongodb.entity.User;
import com.bezkoder.spring.jwt.mongodb.request.PostRequest;
import com.bezkoder.spring.jwt.mongodb.repository.PostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostServiceImpl implements  PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final RoleService roleService;

    public PostServiceImpl(PostRepository accountRepository, UserService userService, RoleService roleService) {
        this.postRepository = accountRepository;
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public Page<Post> getAllPost(PostFilter filter, Pageable pageable) {
        return postRepository.getPostList(filter, pageable);
    }

    @Override
    public Post createPost(PostRequest request) throws ResourceNotFoundException {
        Post post = new Post();
        BeanUtils.copyProperties(request, post);
        Date date = new Date();
        post.setCreatedDate(date);
        post.setLastModifiedDate(date);

        User user = userService.getCurrentLoginUser();

        post.setUser(user);
        return postRepository.save(post);
    }

    @Override
    public Post updateById(String id, PostRequest request) throws ForbiddenException, ResourceNotFoundException {
        Post post = getById(id);

        User userLogin = userService.getCurrentLoginUser();

        if (!post.getUser().equals(userLogin)) {
            throw new ForbiddenException("Bạn không có quyền sửa bài viết này.");
        }

        BeanUtils.copyProperties(request, post);
        post.setLastModifiedDate(new Date());
        postRepository.save(post);
        return post;
    }

    @Override
    public Post getById(String id) throws ResourceNotFoundException {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null){
            throw new ResourceNotFoundException("Post not found!");
        }
        return post;
    }

    @Override
    public void deleteById(String id) throws ResourceNotFoundException, ForbiddenException {
        Post post = getById(id);

        // Xác định người dùng hiện tại
        User userLogin = userService.getCurrentLoginUser();
        Role adminRole = roleService.getRoleAdmin();

        // Kiểm tra xem người dùng hiện tại có phải là người tạo ra bài viết không
        if (!post.getUser().equals(userLogin) && !userLogin.getRoles().contains(adminRole)){
            throw new ForbiddenException("Bạn không có quyền xóa bình luận này.");
        }

        postRepository.deleteById(id);
    }
}
