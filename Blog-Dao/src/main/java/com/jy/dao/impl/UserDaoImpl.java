package com.jy.dao.impl;

import com.jy.dao.UserDao;
import com.jy.entity.User;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User selectUserByUsername(String username) {
        return (User) getCurrentSession().createQuery("select user from User user where user.username = :username").setParameter("username", username, StringType.INSTANCE).uniqueResult();
    }

    @Override
    public User findUserByPhone(String phone) {
        return (User) getCurrentSession().createQuery("select user from User user where user.phone = :phone").setParameter("phone", phone, StringType.INSTANCE).uniqueResult();
    }
}
