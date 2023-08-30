package com.bezkoder.spring.jwt.mongodb.service;

import com.bezkoder.spring.jwt.mongodb.entity.Post;
import com.bezkoder.spring.jwt.mongodb.exception.ForbiddenException;
import com.bezkoder.spring.jwt.mongodb.exception.ResourceNotFoundException;
import com.bezkoder.spring.jwt.mongodb.entity.Comment;
import com.bezkoder.spring.jwt.mongodb.entity.Role;
import com.bezkoder.spring.jwt.mongodb.entity.User;
import com.bezkoder.spring.jwt.mongodb.repository.PostRepository;
import com.bezkoder.spring.jwt.mongodb.request.CommentRequest;
import com.bezkoder.spring.jwt.mongodb.repository.CommentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    private final UserService userService;

    private final RoleService roleService;

    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, RoleService roleService, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.roleService = roleService;
        this.postRepository = postRepository;
    }
    @Override
    public Page<Comment> getAllComment(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Comment createComment(CommentRequest request) throws ResourceNotFoundException {
        User user = userService.getCurrentLoginUser();
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        Comment comment = new Comment();
        BeanUtils.copyProperties(request, comment);

        comment.setUser(user);
        comment.setCreatedDate(new Date());

        comment.setPost(post);

        return commentRepository.save(comment);
    }

    @Override
    public void updateById(String id, CommentRequest request) throws ForbiddenException, ResourceNotFoundException {
        Comment comment = getById(id);

        User userLogin = userService.getCurrentLoginUser();

        if (!comment.getUser().equals(userLogin)) {
            throw new ForbiddenException("Bạn không có quyền sửa bình luận này.");
        }

        BeanUtils.copyProperties(request, comment);
        comment.setLastModifiedDate(new Date());
        commentRepository.save(comment);
    }

    private Comment getById(String id) throws ResourceNotFoundException {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null){
            throw new ResourceNotFoundException("Comment not found!");
        }
        return comment;
    }

    @Override
    public void deleteById(String id) throws ResourceNotFoundException, ForbiddenException {
        Comment comment = getById(id);

        // Xác định người dùng hiện tại
        User userLogin = userService.getCurrentLoginUser();
        String adminRoleId = roleService.getRoleAdmin().getId();
        Set<String> userRoleIds = userLogin.getRoles().stream().map(Role::getId).collect(Collectors.toSet());


        // Kiểm tra xem người dùng hiện tại có phải là người tạo ra bình luận không
        if (!comment.getUser().equals(userLogin) && !userRoleIds.contains(adminRoleId)) {
            throw new ForbiddenException("Bạn không có quyền xóa bình luận này.");
        }

        commentRepository.deleteById(id);
    }
}
