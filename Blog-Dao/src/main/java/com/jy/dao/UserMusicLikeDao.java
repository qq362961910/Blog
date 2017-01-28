package com.jy.dao;

import com.jy.entity.UserMusicLike;

import java.util.List;

public interface UserMusicLikeDao {

    void insertUserMusicLike(Long ownerId, Long musicId);

    UserMusicLike selectUserMusicLikeByOwnerIdAndMusicId(Long ownerId, Long musicId);

    void deleteUserMusicLikeByOwnerIdAndMusicId(Long ownerId, Long musicId);

    void deleteUserMusicLikeByOwnerId(Long ownerId);

    List<UserMusicLike> selectByOwnerId(Long ownerId);
}
