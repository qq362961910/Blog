package com.jy.dao.impl;

import com.jy.dao.DaoHelper;
import com.jy.dao.UserMusicLikeDao;
import com.jy.embedkey.UserMusicEmbedKey;
import com.jy.entity.UserMusicLike;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class UserMusicLikeDaoImpl implements UserMusicLikeDao {

    @Autowired
    private DaoHelper daoHelper;

    @Override
    public void insertUserMusicLike(Long ownerId, Long musicId) {
        UserMusicEmbedKey key = new UserMusicEmbedKey();
        key.setOwnerId(ownerId);
        key.setMusicId(musicId);
        UserMusicLike userMusicLike = new UserMusicLike();
        userMusicLike.setUserMusicEmbedKey(key);
        userMusicLike.setCreateTime(new Date());
        daoHelper.getCurrentSession().save(userMusicLike);
    }

    @Override
    public UserMusicLike selectUserMusicLikeByOwnerIdAndMusicId(Long ownerId, Long musicId) {

        Query query = daoHelper.getCurrentSession().createQuery("select userMusicLike from UserMusicLike userMusicLike where userMusicLike.userMusicEmbedKey.ownerId = :ownerId and userMusicLike.userMusicEmbedKey.musicId = :musicId");
        daoHelper.setHqlParam(query, "ownerId", ownerId);
        daoHelper.setHqlParam(query, "musicId", musicId);
        return (UserMusicLike) query.uniqueResult();
    }

    @Override
    public void deleteUserMusicLikeByOwnerIdAndMusicId(Long ownerId, Long musicId) {
        Query query = daoHelper.getCurrentSession().createQuery("delete from UserMusicLike userMusicLike where userMusicLike.userMusicEmbedKey.ownerId = :ownerId and userMusicLike.userMusicEmbedKey.musicId = :musicId");
        daoHelper.setHqlParam(query, "ownerId", ownerId);
        daoHelper.setHqlParam(query, "musicId", musicId);
        query.executeUpdate();
    }

    @Override
    public void deleteUserMusicLikeByOwnerId(Long ownerId) {
        Query query = daoHelper.getCurrentSession().createQuery("delete from UserMusicLike userMusicLike where userMusicLike.userMusicEmbedKey.ownerId = :ownerId");
        daoHelper.setHqlParam(query, "ownerId", ownerId);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserMusicLike> selectByOwnerId(Long ownerId) {
        Query query = daoHelper.getCurrentSession().createQuery("select userMusicLike from UserMusicLike userMusicLike where userMusicLike.userMusicEmbedKey.ownerId = :ownerId");
        daoHelper.setHqlParam(query, "ownerId", ownerId);
        return query.list();
    }


}
