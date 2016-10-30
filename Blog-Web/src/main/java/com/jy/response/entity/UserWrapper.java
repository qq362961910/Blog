package com.jy.response.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserWrapper extends BaseWrapper{

    /**
     * 用戶名
     * */
    @JsonProperty("username")
    private String username;

    public String getUsername() {
        return username;
    }

    public UserWrapper setUsername(String username) {
        this.username = username;
        return this;
    }
}
