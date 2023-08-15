package com.bezkoder.spring.jwt.mongodb.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import static com.bezkoder.spring.jwt.mongodb.constants.Constants.MAX_TITLE_LENGTH;

public class PostRequest {

    @Size(max = MAX_TITLE_LENGTH, message = "Title must be at least " + MAX_TITLE_LENGTH + " characters long")
    @NotBlank(message = "Please enter the title")
    private String title;

    @NotBlank(message = "Viết gì đi anh zai...")
    private String body;

    public PostRequest(String title, String body) {
        this.title = title;
        this.body = body;
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
}
