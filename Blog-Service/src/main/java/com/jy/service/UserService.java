package com.jy.service;

import com.jy.entity.User;

import java.security.NoSuchAlgorithmException;

/**
 * User Interfacve
 */
public interface UserService extends BaseService<User> {

    User findUserByUsername(String username);

    User findUserByPhone(String phone);

    User save(String phone) throws NoSuchAlgorithmException;
}
