package com.jy.entity;

import javax.persistence.*;

@Entity
@Table(name = "blog_mood")
public class Mood extends BaseEntity{

    /**
     * 图片
     * */
    @Column(name = "cover_image")
    private String coverImage;

    /**
     * 内容
     * */
    @Column(name = "content")
    private String content;

    /**
     * 删除状态
     * */
    @Column(name = "deleted")
    private Boolean deleted;

    /**
     * 作者
     * */
    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "username")
    private User owner;

    public String getCoverImage() {
        return coverImage;
    }

    public Mood setCoverImage(String coverImage) {
        this.coverImage = coverImage;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Mood setContent(String content) {
        this.content = content;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Mood setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Mood setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    @Override
    public String toString() {
        return "Mood{" +
                "coverImage='" + coverImage + '\'' +
                ", content='" + content + '\'' +
                ", deleted=" + deleted +
                ", owner=" + owner +
                '}';
    }
}
