package com.bezkoder.spring.jwt.mongodb.dto;


import java.util.Date;

public class PostDTO {
    private String id;

    private String title;

    private String body;

    private Date createdDate;

    private UserDTO user;

    private Date lastModifiedDate;

    public PostDTO() {
    }

    public PostDTO(String title, String body, Date createdDate, UserDTO user, Date lastModifiedDate) {
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
