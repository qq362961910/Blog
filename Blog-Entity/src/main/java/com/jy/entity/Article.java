package com.jy.entity;

import javax.persistence.*;

/**
 * 文章
 * */
@Entity
@Table(name = "blog_article")
public class Article extends BaseEntity{

    /**
     * 标题
     * */
    @Column(name = "title")
    private String title;

    /**
     * 封面
     * */
    private String coverImage;

    /**
     * 发布者
     * */
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    /**
     * 阅读量
     * */
    @Column(name = "read_count")
    private Integer readCount;

    /**
     * 点赞数量
     * */
    @Column(name = "like_count")
    private Integer likeCount;

    /**
     * 摘要
     * */
    @Column(name = "summary")
    private String summary;

    /**
     * 内容
     * */
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    /**
     * 关键字
     * */
    @Column(name = "keyworks")
    private String keyworks;

    /**
     * 推荐
     * */
    @Column(name = "recommended", columnDefinition = "BIT")
    private Boolean recommended;

    /**
     * 删除状态
     * */
    @Column(name = "deleted")
    private Boolean deleted;

    public String getTitle() {
        return title;
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public Article setCoverImage(String coverImage) {
        this.coverImage = coverImage;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Article setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public Article setReadCount(Integer readCount) {
        this.readCount = readCount;
        return this;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public Article setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Article setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Article setContent(String content) {
        this.content = content;
        return this;
    }

    public String getKeyworks() {
        return keyworks;
    }

    public Article setKeyworks(String keyworks) {
        this.keyworks = keyworks;
        return this;
    }

    public Boolean getRecommended() {
        return recommended;
    }

    public Article setRecommended(Boolean recommended) {
        this.recommended = recommended;
        return this;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public Article setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", coverImage='" + coverImage + '\'' +
                ", owner=" + owner +
                ", readCount=" + readCount +
                ", likeCount=" + likeCount +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", keyworks='" + keyworks + '\'' +
                ", recommended=" + recommended +
                ", deleted=" + deleted +
                '}';
    }
}
