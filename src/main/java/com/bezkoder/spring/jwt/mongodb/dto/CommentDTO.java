package com.bezkoder.spring.jwt.mongodb.dto;

import com.bezkoder.spring.jwt.mongodb.entity.Post;
import com.bezkoder.spring.jwt.mongodb.entity.User;

import java.util.Date;

public class CommentDTO {

    private String body;

    private Date createdDate;

    private User user;

    private Post post;

    public CommentDTO(String body, Date createdDate, User user, Post post) {
        this.body = body;
        this.createdDate = createdDate;
        this.user = user;
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
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
