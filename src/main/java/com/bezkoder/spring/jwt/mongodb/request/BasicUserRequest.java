package com.bezkoder.spring.jwt.mongodb.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import static com.bezkoder.spring.jwt.mongodb.constants.Constants.MIN_USERNAME_LENGTH;
import static com.bezkoder.spring.jwt.mongodb.constants.Constants.STRING_MAX_LENGTH;

public class BasicUserRequest {

    @Size(min = MIN_USERNAME_LENGTH)
    @NotBlank(message = "Please enter username")
    private String username;

    @NotBlank(message = "Please enter the full name")
    @Size(max = STRING_MAX_LENGTH)
    private String fullName;

    @NotBlank(message = "Please enter the email")
    @Size(max = STRING_MAX_LENGTH)
    @Email(message = "Invalid email format")
    private String email;

    public BasicUserRequest(String username, String fullName, String email) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
