package com.jy.dao.impl;

import com.jy.dao.DaoHelper;
import com.jy.dao.UserBookLikeDao;
import com.jy.embedkey.UserBookEmbedKey;
import com.jy.entity.UserBookLike;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class UserBookLikeDaoImpl implements UserBookLikeDao {

    @Autowired
    private DaoHelper daoHelper;

    @Override
    public void insertUserBookLike(Long ownerId, Long bookId) {
        UserBookEmbedKey key = new UserBookEmbedKey();
        key.setOwnerId(ownerId);
        key.setBookId(bookId);
        UserBookLike userBookLike = new UserBookLike();
        userBookLike.setUserBookEmbedKey(key);
        userBookLike.setCreateTime(new Date());
        daoHelper.getCurrentSession().save(userBookLike);
    }

    @Override
    public UserBookLike selectUserBookLikeByOwnerIdAndBookId(Long ownerId, Long bookId) {

        Query query = daoHelper.getCurrentSession().createQuery("select userBookLike from UserBookLike userBookLike where userBookLike.userBookEmbedKey.ownerId = :ownerId and userBookLike.userBookEmbedKey.bookId = :bookId");
        daoHelper.setHqlParam(query, "ownerId", ownerId);
        daoHelper.setHqlParam(query, "bookId", bookId);
        return (UserBookLike) query.uniqueResult();
    }

    @Override
    public void deleteUserBookLikeByOwnerIdAndBookId(Long ownerId, Long bookId) {
        Query query = daoHelper.getCurrentSession().createQuery("delete from UserBookLike userBookLike where userBookLike.userBookEmbedKey.ownerId = :ownerId and userBookLike.userBookEmbedKey.bookId = :bookId");
        daoHelper.setHqlParam(query, "ownerId", ownerId);
        daoHelper.setHqlParam(query, "bookId", bookId);
        query.executeUpdate();
    }

    @Override
    public void deleteUserBookLikeByOwnerId(Long ownerId) {
        Query query = daoHelper.getCurrentSession().createQuery("delete from UserBookLike userBookLike where userBookLike.userBookEmbedKey.ownerId = :ownerId");
        daoHelper.setHqlParam(query, "ownerId", ownerId);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserBookLike> selectByOwnerId(Long ownerId) {
        Query query = daoHelper.getCurrentSession().createQuery("select userBookLike from UserBookLike userBookLike where userBookLike.userBookEmbedKey.ownerId = :ownerId");
        daoHelper.setHqlParam(query, "ownerId", ownerId);
        return query.list();
    }
}
