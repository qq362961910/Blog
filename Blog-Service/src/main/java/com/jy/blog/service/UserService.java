package com.jy.blog.service;

import com.jy.blog.entity.User;

import java.security.NoSuchAlgorithmException;

/**
 * User Interfacve
 */
public interface UserService extends BaseService<User> {

    User findUserByUsername(String username);

    User findUserByUsernameAndPassword(String username, String password) throws NoSuchAlgorithmException;

    User findUserByPhone(String phone);

    User save(String phone) throws NoSuchAlgorithmException;
}
