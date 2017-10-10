package com.jy.dao;

import com.jy.entity.User;

/**
 * User Dao
 */
public interface UserDao extends BaseDao<User> {

    User selectUserByUsername(String username);

    User findUserByPhone(String phone);
}
