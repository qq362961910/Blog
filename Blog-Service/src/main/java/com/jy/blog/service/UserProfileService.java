package com.jy.blog.service;

import com.jy.blog.entity.UserBookLike;
import com.jy.blog.entity.UserProfile;

import java.util.List;

public interface UserProfileService extends BaseService<UserProfile> {
    UserProfile findByUsername(String username);

    UserProfile findByOwnerId(Long ownerId);

    void saveUserBookLike(long ownerId, long bookId);

    void saveUserMusicLike(long ownerId, long musicId);

    UserBookLike findUserBookLikeByOwnerIdAndBookId(Long ownerId, Long bookId);

    void removeUserBookLikeByOwnerIdAndBookId(Long ownerId, Long bookId);

    void removeUserBookLikeByOwnerId(Long ownerId);

    List<UserBookLike> findUserBookLikeByOwnerId(Long ownerId);
}
