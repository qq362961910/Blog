package com.jy.entity;

import com.jy.embedkey.UserMusicEmbedKey;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户喜欢的音乐
 */
@Entity
@Table(name = "blog_user_music_like")
public class UserMusicLike {

    @EmbeddedId
    private UserMusicEmbedKey userMusicEmbedKey;

    @Column(name = "create_time", columnDefinition = "datetime")
    private Date createTime;

    public UserMusicEmbedKey getUserMusicEmbedKey() {
        return userMusicEmbedKey;
    }

    public UserMusicLike setUserMusicEmbedKey(UserMusicEmbedKey userMusicEmbedKey) {
        this.userMusicEmbedKey = userMusicEmbedKey;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public UserMusicLike setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "UserMusicLike{" +
            "userMusicEmbedKey=" + userMusicEmbedKey +
            ", createTime=" + createTime +
            '}';
    }
}
