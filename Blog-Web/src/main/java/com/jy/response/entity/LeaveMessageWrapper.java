package com.jy.response.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class LeaveMessageWrapper {

    @JsonProperty("fromUser")
    private UserWrapper fromUser;

    @JsonProperty("toUser")
    private UserWrapper toUser;

    @JsonProperty("content")
    private String content;

    @JsonProperty("like_count")
    private Integer likeCount;

    public UserWrapper getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserWrapper fromUser) {
        this.fromUser = fromUser;
    }

    public UserWrapper getToUser() {
        return toUser;
    }

    public void setToUser(UserWrapper toUser) {
        this.toUser = toUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }
}
