package com.bezkoder.spring.jwt.mongodb.entity;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

import static com.bezkoder.spring.jwt.mongodb.constants.Constants.MAX_TITLE_LENGTH;


@Document(collection = "posts")
public class Post {

    @Id
    private String id;

    @Size(max = MAX_TITLE_LENGTH, message = "Title must be at least " + MAX_TITLE_LENGTH + " characters long")
    @NotBlank(message = "Please enter the title")
    private String title;

    @NotBlank(message = "Viết gì đi anh zai...")
    private String body;

    @CreatedDate
    private Date createdDate;

    @DBRef
    private User user;

    @Field("lastModifiedDate")
    private Date lastModifiedDate;

    public Post(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Post() {
    }

    public Post(String id, String title, String body, Date createdDate, User user, Date lastModifiedDate) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.createdDate = createdDate;
        this.user = user;
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", creationDate=" + createdDate +
                "}";
    }
}

