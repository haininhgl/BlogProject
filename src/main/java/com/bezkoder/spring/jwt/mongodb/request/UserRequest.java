package com.bezkoder.spring.jwt.mongodb.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

import static com.bezkoder.spring.jwt.mongodb.constants.Constants.STRING_MAX_LENGTH;


public class UserRequest {

    @Size(max = STRING_MAX_LENGTH)
    @NotBlank
    private String username;

    private String password;

    private String email;

    protected  Set<String> roleIds = new HashSet<>();

    public UserRequest() {
    }

    public UserRequest(String username, String password, String email, Set<String> roleIds) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roleIds = roleIds;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Set<String> roleIds) {
        this.roleIds = roleIds;
    }
}
