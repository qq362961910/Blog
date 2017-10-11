package com.jy.blog.service.impl;

import com.jy.blog.blog.dao.UserBookLikeDao;
import com.jy.blog.blog.dao.BaseDao;
import com.jy.blog.blog.dao.UserMusicLikeDao;
import com.jy.blog.blog.dao.UserProfileDao;
import com.jy.blog.entity.UserMusicLike;
import com.jy.blog.entity.UserProfile;
import com.jy.blog.entity.UserBookLike;
import com.jy.blog.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class UserProfileServiceImpl extends BaseServiceImpl<UserProfile> implements UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;
    @Autowired
    private UserBookLikeDao userBookLikeDao;
    @Autowired
    private UserMusicLikeDao userMusicLikeDao;

    @Override
    public UserProfile findByUsername(String username) {
        return userProfileDao.selectByUsername(username);
    }

    @Override
    public UserProfile findByOwnerId(Long ownerId) {
        return userProfileDao.selectByOwnerId(ownerId);
    }

    @Override
    public void saveUserBookLike(long ownerId, long bookId) {
        UserBookLike userBookLike = userBookLikeDao.selectUserBookLikeByOwnerIdAndBookId(ownerId, bookId);
        if (userBookLike == null) {
            userBookLikeDao.insertUserBookLike(ownerId, bookId);
        }
    }

    @Override
    public void saveUserMusicLike(long ownerId, long musicId) {
        UserMusicLike userMusicLike = userMusicLikeDao.selectUserMusicLikeByOwnerIdAndMusicId(ownerId, musicId);
        if (userMusicLike == null) {
            userMusicLikeDao.insertUserMusicLike(ownerId, musicId);
        }
    }

    @Override
    public UserBookLike findUserBookLikeByOwnerIdAndBookId(Long ownerId, Long bookId) {
        return userBookLikeDao.selectUserBookLikeByOwnerIdAndBookId(ownerId, bookId);
    }

    @Override
    public void removeUserBookLikeByOwnerIdAndBookId(Long ownerId, Long bookId) {
        userBookLikeDao.deleteUserBookLikeByOwnerIdAndBookId(ownerId, bookId);
    }

    @Override
    public void removeUserBookLikeByOwnerId(Long ownerId) {
        userBookLikeDao.deleteUserBookLikeByOwnerId(ownerId);
    }

    @Override
    public List<UserBookLike> findUserBookLikeByOwnerId(Long ownerId) {
        return userBookLikeDao.selectByOwnerId(ownerId);
    }

    @Override
    public BaseDao<UserProfile> getBaseDao() {
        return userProfileDao;
    }
}
