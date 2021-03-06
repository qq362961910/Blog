package com.jy.blog.service.impl;

import com.jy.blog.blog.common.util.PasswordUtil;
import com.jy.blog.blog.dao.UserDao;
import com.jy.blog.blog.dao.BaseDao;
import com.jy.blog.service.UserService;
import com.jy.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findUserByUsername(String username) {
        return userDao.selectUserByUsername(username);
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) throws NoSuchAlgorithmException {
        return userDao.findUserByUsernameAndPassword(username, PasswordUtil.createPassword(password));
    }

    @Override
    public User findUserByPhone(String phone) {
        return userDao.findUserByPhone(phone);
    }

    @Override
    public User save(String phone) throws NoSuchAlgorithmException {
        User user = new User();
        user.setAvatar("");
        user.setEmail("");
        user.setNickname("");
        user.setPassword(PasswordUtil.createPassword("123456"));
        user.setPhone(phone);
        user.setSex(null);
        user.setCreateTime(new Date());
        save(user);
        user = findUserByPhone(phone);
        return user;
    }

    @Override
    public BaseDao<User> getBaseDao() {
        return userDao;
    }
}
