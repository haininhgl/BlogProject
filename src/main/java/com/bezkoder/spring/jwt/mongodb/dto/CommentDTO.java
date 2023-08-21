package com.bezkoder.spring.jwt.mongodb.dto;

import com.bezkoder.spring.jwt.mongodb.entity.Post;
import com.bezkoder.spring.jwt.mongodb.entity.User;

import java.util.Date;

public class CommentDTO {

    private String body;

    private Date createdDate;

    private UserDTO user;

    private PostDTO post;

    public CommentDTO(String body, Date createdDate, UserDTO user, PostDTO post) {
        this.body = body;
        this.createdDate = createdDate;
        this.user = user;
        this.post = post;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
