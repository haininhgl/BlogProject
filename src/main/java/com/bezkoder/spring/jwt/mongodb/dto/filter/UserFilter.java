package com.bezkoder.spring.jwt.mongodb.dto.filter;

import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

import static com.bezkoder.spring.jwt.mongodb.constants.Constants.STRING_MAX_LENGTH;

public class UserFilter {

    @Size(max = STRING_MAX_LENGTH)
    private String text = "";

    private Set<String> roleIds = new HashSet<>();


    public UserFilter() {}

    public UserFilter(Set<String> roleIds,String text) {
        this.roleIds = roleIds;
        this.text = text;
    }

    public String getText() {
        return StringUtils.trim(text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Set<String> roleIds) {
        this.roleIds = roleIds;
    }
}