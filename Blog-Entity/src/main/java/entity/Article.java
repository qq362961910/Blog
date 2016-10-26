package entity;

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
     * 发布者
     * */
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    /**
     * 阅读量
     * */
    @Column(name = "")
    private int count;

    /**
     * 点赞数量
     * */
    @Column(name = "")
    private int likeCount;

    /**
     * 摘要
     * */
    @Column(name = "")
    private String summary;

    /**
     * 内容
     * */
    @Column(name = "")
    private String content;

    /**
     * 关键字
     * */
    @Column(name = "")
    private String keyworks;

    /**
     * 删除状态
     * */
    @Column(name = "")
    private boolean deleted;

    public String getTitle() {
        return title;
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Article setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public int getCount() {
        return count;
    }

    public Article setCount(int count) {
        this.count = count;
        return this;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public Article setLikeCount(int likeCount) {
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

    public boolean isDeleted() {
        return deleted;
    }

    public Article setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }
}
