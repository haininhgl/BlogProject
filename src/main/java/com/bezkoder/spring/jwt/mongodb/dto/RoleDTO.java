package com.bezkoder.spring.jwt.mongodb.dto;

import com.bezkoder.spring.jwt.mongodb.entity.RoleType;

public class RoleDTO {

    private String id;
    private String name;

    private boolean isSystemRole;

    public RoleDTO() {
    }

    public RoleDTO(String id, String name, boolean isSystemRole) {
        this.id = id;
        this.name = name;
        this.isSystemRole = isSystemRole;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSystemRole() {
        return isSystemRole;
    }

    public void setSystemRole(boolean systemRole) {
        isSystemRole = systemRole;
    }
}
