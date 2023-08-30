package com.bezkoder.spring.jwt.mongodb.dto;

import java.util.Set;

public class UserDTO {

    private String id;
    private String username;

    private String email;

    private Set<RoleDTO> roles;

    public UserDTO() {
    }

    public UserDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public UserDTO(String id, String username, String email, Set<RoleDTO> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}

