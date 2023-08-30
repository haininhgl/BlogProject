package com.bezkoder.spring.jwt.mongodb.request;

import jakarta.validation.constraints.NotBlank;

public class CommentRequest {

    @NotBlank(message = "Bình luận gì đi?")
    private String body;

    @NotBlank(message = "Bài viết nào?")
    private String postId;

    public CommentRequest() {
    }

    public CommentRequest(String body, String postId) {
        this.body = body;
        this.postId = postId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
