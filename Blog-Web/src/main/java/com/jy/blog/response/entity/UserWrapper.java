package com.jy.blog.response.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class UserWrapper extends BaseWrapper {

    /**
     * 用戶名
     */
    @JsonProperty("username")
    private String username;

    /**
     * 昵称
     */
    @JsonProperty("nickname")
    private String nickname;

    /**
     * 头像
     */
    @JsonProperty("avatar")
    private String avatar;

    /**
     * 详细资料信息
     */
    @JsonProperty("profile")
    private UserProfileWrapper userProfileWrapper;

    public String getUsername() {
        return username;
    }

    public UserWrapper setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UserWrapper setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public UserWrapper setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public UserProfileWrapper getUserProfileWrapper() {
        return userProfileWrapper;
    }

    public UserWrapper setUserProfileWrapper(UserProfileWrapper userProfileWrapper) {
        this.userProfileWrapper = userProfileWrapper;
        return this;
    }
}
