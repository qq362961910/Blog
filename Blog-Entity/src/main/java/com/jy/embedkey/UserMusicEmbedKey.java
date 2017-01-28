package com.jy.embedkey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserMusicEmbedKey  implements Serializable {

    @Column(name = "user_id")
    private Long ownerId;

    @Column(name = "music_id")
    private Long musicId;

    public Long getOwnerId() {
        return ownerId;
    }

    public UserMusicEmbedKey setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public Long getMusicId() {
        return musicId;
    }

    public UserMusicEmbedKey setMusicId(Long musicId) {
        this.musicId = musicId;
        return this;
    }

    @Override
    public String toString() {
        return "UserMusicEmbedKey{" +
                "ownerId=" + ownerId +
                ", musicId=" + musicId +
                '}';
    }
}
