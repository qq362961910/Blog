package com.jy.blog.entity;

import com.jy.blog.embedkey.UserBookEmbedKey;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户喜欢的书
 */
@Entity
@Table(name = "blog_user_book_like")
public class UserBookLike {

    @EmbeddedId
    private UserBookEmbedKey userBookEmbedKey;

    @Column(name = "create_time", columnDefinition = "datetime")
    private Date createTime;

    public UserBookEmbedKey getUserBookEmbedKey() {
        return userBookEmbedKey;
    }

    public UserBookLike setUserBookEmbedKey(UserBookEmbedKey userBookEmbedKey) {
        this.userBookEmbedKey = userBookEmbedKey;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public UserBookLike setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
