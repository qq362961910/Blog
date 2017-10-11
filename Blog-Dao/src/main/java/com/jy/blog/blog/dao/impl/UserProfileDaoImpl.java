package com.jy.blog.blog.dao.impl;

import com.jy.blog.blog.dao.UserDao;
import com.jy.blog.blog.dao.UserProfileDao;
import com.jy.blog.entity.User;
import com.jy.blog.entity.UserProfile;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserProfileDaoImpl extends BaseDaoImpl<UserProfile> implements UserProfileDao {

    @Autowired
    private UserDao userDao;

    @Override
    public UserProfile selectByUsername(String username) {
        User user = userDao.selectUserByUsername(username);
        if (user == null) {
            return null;
        }
        Query query = getCurrentSession().createQuery("select userProfile from UserProfile userProfile where userProfile.ownerId = :ownerId");
        return (UserProfile) setHqlParam(query, "ownerId", user.getId()).uniqueResult();
    }

    @Override
    public UserProfile selectByOwnerId(Long ownerId) {
        Query query = getCurrentSession().createQuery("select userProfile from UserProfile userProfile where userProfile.ownerId = :ownerId");
        return (UserProfile) setHqlParam(query, "ownerId", ownerId).uniqueResult();
    }
}
