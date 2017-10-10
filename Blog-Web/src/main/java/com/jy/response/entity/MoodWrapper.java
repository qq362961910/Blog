package com.jy.response.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class MoodWrapper extends BaseWrapper {

    /**
     * 封面图
     */
    @JsonProperty("coverImage")
    private String coverImage;

    /**
     * 内容
     */
    @JsonProperty("content")
    private String content;

    /**
     * 发布时间
     */
    @JsonProperty("createTime")
    private String createTime;

    /**
     * 发布者
     */
    @JsonProperty("owner")
    private UserWrapper owner;

    public String getCoverImage() {
        return coverImage;
    }

    public MoodWrapper setCoverImage(String coverImage) {
        this.coverImage = coverImage;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MoodWrapper setContent(String content) {
        this.content = content;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public MoodWrapper setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public UserWrapper getOwner() {
        return owner;
    }

    public MoodWrapper setOwner(UserWrapper owner) {
        this.owner = owner;
        return this;
    }
}
