package com.jy.embedkey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserBookEmbedKey implements Serializable {

    @Column(name = "user_id")
    private Long ownerId;

    @Column(name = "book_id")
    private Long bookId;

    public Long getOwnerId() {
        return ownerId;
    }

    public UserBookEmbedKey setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public Long getBookId() {
        return bookId;
    }

    public UserBookEmbedKey setBookId(Long bookId) {
        this.bookId = bookId;
        return this;
    }

    @Override
    public String toString() {
        return "UserBookEmbedKey{" +
            "ownerId=" + ownerId +
            ", bookId=" + bookId +
            '}';
    }
}
