package com.jy.blog.blog.dao;

import com.jy.blog.entity.User;

/**
 * User Dao
 */
public interface UserDao extends BaseDao<User> {

    User selectUserByUsername(String username);

    User findUserByUsernameAndPassword(String username, String password);

    User findUserByPhone(String phone);
}
