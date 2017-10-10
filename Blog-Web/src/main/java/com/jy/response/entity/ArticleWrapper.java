package com.jy.response.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class ArticleWrapper extends BaseWrapper {

    /**
     * 标题
     */
    @JsonProperty("title")
    private String title;

    /**
     * 封面
     */
    @JsonProperty("coverImage")
    private String coverImage;

    /**
     * 发布者
     */
    @JsonProperty("owner")
    private UserWrapper owner;

    /**
     * 发布時間
     */
    @JsonProperty("createTime")
    private String createTime;

    /**
     * 阅读量
     */
    @JsonProperty("readCount")
    private int readCount;

    /**
     * 点赞数量
     */
    @JsonProperty("likeCount")
    private int likeCount;

    /**
     * 摘要
     */
    @JsonProperty("summary")
    private String summary;

    /**
     * 内容
     */
    @JsonProperty("content")
    private String content;

    /**
     * 关键字
     */
    @JsonProperty("keyworks")
    private String keyworks;

    public String getTitle() {
        return title;
    }

    public ArticleWrapper setTitle(String title) {
        this.title = title;
        return this;
    }

    public UserWrapper getOwner() {
        return owner;
    }

    public ArticleWrapper setOwner(UserWrapper owner) {
        this.owner = owner;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public ArticleWrapper setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public int getReadCount() {
        return readCount;
    }

    public ArticleWrapper setReadCount(int readCount) {
        this.readCount = readCount;
        return this;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public ArticleWrapper setLikeCount(int likeCount) {
        this.likeCount = likeCount;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public ArticleWrapper setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ArticleWrapper setContent(String content) {
        this.content = content;
        return this;
    }

    public String getKeyworks() {
        return keyworks;
    }

    public ArticleWrapper setKeyworks(String keyworks) {
        this.keyworks = keyworks;
        return this;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public ArticleWrapper setCoverImage(String coverImage) {
        this.coverImage = coverImage;
        return this;
    }
}
