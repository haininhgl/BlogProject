package com.bezkoder.spring.jwt.mongodb.controllers;

import com.bezkoder.spring.jwt.mongodb.dto.PostDTO;
import com.bezkoder.spring.jwt.mongodb.dto.filter.PostFilter;
import com.bezkoder.spring.jwt.mongodb.dto.mapper.PostMapper;
import com.bezkoder.spring.jwt.mongodb.exception.ForbiddenException;
import com.bezkoder.spring.jwt.mongodb.exception.ResourceNotFoundException;
import com.bezkoder.spring.jwt.mongodb.entity.Post;
import com.bezkoder.spring.jwt.mongodb.request.PaginationRequest;
import com.bezkoder.spring.jwt.mongodb.request.PostRequest;
import com.bezkoder.spring.jwt.mongodb.response.APIResponse;
import com.bezkoder.spring.jwt.mongodb.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@Validated
public class PostController {

    @Value("${maxPageSize}")
    private Integer maxPageSize;

    private final PostService postService;

    private final PostMapper postMapper;

    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    //Xem tất cả post
    @GetMapping("/posts")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public APIResponse<List<PostDTO>> getPostList(@Valid PostFilter filter, PaginationRequest paginationRequest)
            throws ResourceNotFoundException {

        Pageable pageable = paginationRequest.toPageable(maxPageSize);
        Page<Post> postPage = postService.getAllPost(filter,pageable);
        Page<PostDTO> result = postPage.map(this.postMapper::toDto);

        return APIResponse.newSuccessPageResponse(result);
    }

    //Xem post theo tên
    @GetMapping("/posts/search/{title}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public APIResponse<List<PostDTO>> getPostByTitle(@PathVariable String title) throws ResourceNotFoundException {
        List<PostDTO> posts;

        posts = postService.getPostsByTitle(title);
        return APIResponse.newSuccessResponse(posts);
    }

    //Tạo post
    @PostMapping("/posts")
    @PreAuthorize("hasRole('USER')")
    public APIResponse<PostDTO> createPost(@Valid @RequestBody PostRequest request) {
        Post post = postService.createPost(request);
        PostDTO postDTO = postMapper.toDto(post);
        return APIResponse.newSuccessResponse(postDTO);
    }

    //Cập nhật post theo id
    @PutMapping("/posts/{id}")
    @PreAuthorize("hasRole('USER')")
    public APIResponse<Post> updatePost(@PathVariable String id, @RequestBody PostRequest request) throws ForbiddenException {
        Post post = postService.updateById(id, request);
        return APIResponse.newSuccessResponse(post);
    }

    //Xóa post theo id
    @DeleteMapping("/accounts/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public APIResponse<Post> deletePostById(@PathVariable String id) throws ResourceNotFoundException, ForbiddenException {
        postService.deleteById(id);
        return APIResponse.newSuccessResponse();
    }
}