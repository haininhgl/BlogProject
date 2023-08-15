package com.bezkoder.spring.jwt.mongodb.entity;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    @NotBlank(message = "Bình luận gì đi?")
    private String body;

    @CreatedDate
    private Date createdDate;

    @DBRef
    private User user;

    @DBRef
    private Post post;

    @Field("lastModifiedDate")
    private Date lastModifiedDate;

    public Comment(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Comment() {
    }

    public Comment(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public Comment(String id, String body, Date createdDate, User user, Post post) {
        this.id = id;
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

    public Comment(String id, String body, Date createdDate) {
        this.id = id;
        this.body = body;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreationDate() {
        return createdDate;
    }

    public void setCreationDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", creationDate=" + createdDate +
                "}";
    }
}



